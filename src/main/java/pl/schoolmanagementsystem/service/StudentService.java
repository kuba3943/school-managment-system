package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.dto.StudentDto;
import pl.schoolmanagementsystem.model.*;
import pl.schoolmanagementsystem.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final GradeService gradeService;
    private final PointRepository pointRepository;
    private final ClassService classService;
    private final SchoolSubjectRepository subjectRepository;


    public StudentDto convertStudentToDto (Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setDOB(student.getDOB());
        studentDto.setGrades(student.getGrades());
        studentDto.setName(student.getName());
        studentDto.setPassword(student.getPassword());
        studentDto.setSurname(student.getSurname());
        studentDto.setUserRole(student.getUserRole());
        studentDto.setUsername(student.getUsername());
        return studentDto;
    }

    public StudentDto add(Student student) {
        studentRepository.save(student);
        return convertStudentToDto(student);
    }

    public StudentDto get(Long id) {
       return convertStudentToDto(studentRepository
               .findById(id).orElseThrow(() -> new RuntimeException("Could find student")));

    }


    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(this::convertStudentToDto).collect(Collectors.toList());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findStudentByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found in database!"));
    }

    public Optional<Student> findByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }

    public StudentDto findStudentDto(String username){
        return convertStudentToDto(studentRepository.findStudentByUsername(username).orElseThrow());
    }


    public StudentDto addGrade(Long studentId, Long subjectId, Long pointId) {

        Student student = studentRepository.findById(studentId).orElseThrow();

        SClass studentClass = classRepository.findSClassByStudentListContains(student);
        SchoolSubject studentSubject = studentClass.getSubjects().stream()
                .filter(a -> a.getId().equals(subjectId)).findAny()
                .orElseThrow(() -> new RuntimeException("Could find class"));

        List<Long> subjectGradesId = studentSubject.getGrades().stream().map(g -> g.getId()).collect(Collectors.toList());

        Grades grades1 = new Grades();
        boolean studentHasGrades = false;
        for (Long grades : subjectGradesId) {
            if (student.getGrades().stream().anyMatch(a -> a.getId().equals(grades))) {
                gradeService.addPointToGrade(grades, pointId);
                studentHasGrades = true;
            }
        }

        if (!studentHasGrades) {
            gradeService.addGrade(grades1);
            gradeService.addPointToGrade(grades1.getId(), pointId);


            newGradesInStudent(student, studentSubject, grades1);

        }


        return convertStudentToDto(student);
    }

    public void newGradesInStudent(Student student, SchoolSubject subject, Grades grades){
        student.getGrades().add(grades);
        studentRepository.save(student);

        subject.getGrades().add(grades);
        subjectRepository.save(subject);
    }

    public StudentDto update(Student student) {
        return convertStudentToDto(studentRepository.save(student));
    }

    public StudentDto delete(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        SClass studentClass = classRepository.findSClassByStudentListContains(student);
        classService.deleteStudentFromClass(studentClass.getId(),studentId);
        studentRepository.delete(studentRepository.findById(studentId).orElseThrow());

        return convertStudentToDto(student);
    }

    public Grades getStudentGradesBySubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        SClass studentClass = classRepository.findSClassByStudentListContains(student);
        SchoolSubject studentSubject = studentClass.getSubjects().stream()
                .filter(a -> a.getId().equals(subjectId)).findAny()
                .orElseThrow(() -> new RuntimeException("Could find class"));

        List<Long> subjectGradesId = studentSubject.getGrades().stream().map(g -> g.getId()).collect(Collectors.toList());

        Grades grades1 = new Grades();
        for (Long grades : subjectGradesId) {
            if (student.getGrades().stream().anyMatch(a -> a.getId().equals(grades))) {
                grades1 = gradeService.get(grades);
            }
        }

        return grades1;


    }

    public StudentDto deleteGrade(Long studentId, Long subjectId, Long pointId) {

        Student student = studentRepository.findById(studentId).orElseThrow();

        SClass studentClass = classRepository.findSClassByStudentListContains(student);
        SchoolSubject studentSubject = studentClass.getSubjects().stream()
                .filter(a -> a.getId().equals(subjectId)).findAny()
                .orElseThrow(() -> new RuntimeException("Could find class"));

        List<Long> subjectGradesId = studentSubject.getGrades().stream().map(g -> g.getId()).collect(Collectors.toList());

//        Grades grades1 = new Grades();
        boolean studentHasGrades = false;
        for (Long grades : subjectGradesId) {
            if (student.getGrades().stream().anyMatch(a -> a.getId().equals(grades))) {
                if (gradeService.get(grades).getPoints().contains(pointRepository.findById(pointId).get())) {
                    gradeService.delete(grades, pointId);
                    studentHasGrades = true;
                }
            }
        }

        if (!studentHasGrades) {
            throw new RuntimeException("there is no this point");
        }


        return convertStudentToDto(student);
    }

    public List<SchoolSubject> getStudentsSubject(Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow();

        SClass studentClass = classRepository.findSClassByStudentListContains(student);

        return studentClass.getSubjects();

    }


    public List<StudentDto> getStudentsWithNoClass(){
        List<StudentDto> studentsWithNoClass = new ArrayList<>();

        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            SClass studentClass = classRepository.findSClassByStudentListContains(student);
            if (studentClass == null){
                studentsWithNoClass.add(convertStudentToDto(student));
            }
        }

        return studentsWithNoClass;
    }

    public boolean getStudentClass (Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow();

        SClass studentClass = classRepository.findSClassByStudentListContains(student);

        if (studentClass != null){
            return true;
        } else {
            return false;
        }

    }





}
