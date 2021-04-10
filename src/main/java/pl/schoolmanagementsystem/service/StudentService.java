package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.*;
import pl.schoolmanagementsystem.repository.ClassRepository;
import pl.schoolmanagementsystem.repository.GradesRepository;
import pl.schoolmanagementsystem.repository.PointRepository;
import pl.schoolmanagementsystem.repository.StudentRepository;

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

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student get(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Could find student"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findStudentByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found in database!"));
    }

    public Optional<Student> findByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }


    public Student addGrade(Long studentId, Long subjectId, Long pointId) {

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
            student.getGrades().add(grades1);
            studentSubject.getGrades().add(grades1);
        }


        return student;
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long studentId) {
        studentRepository.delete(studentRepository.findById(studentId).orElseThrow());
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

    public Student deleteGrade(Long studentId, Long subjectId, Long pointId) {

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
                if (gradeService.get(grades).getPoints().contains(pointRepository.findById(pointId).get())) {
                    gradeService.delete(grades, pointId);
                    studentHasGrades = true;
                }
            }
        }

        if (!studentHasGrades) {
            throw new RuntimeException("there is no this point");
        }


        return student;
    }


}
