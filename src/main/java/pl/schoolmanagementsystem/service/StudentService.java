package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.Point;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.repository.ClassRepository;
import pl.schoolmanagementsystem.repository.GradesRepository;
import pl.schoolmanagementsystem.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService  implements UserDetailsService {

private final StudentRepository studentRepository;
private final ClassRepository classRepository;
private final GradeService gradeService;

public Student add(Student student){
    return studentRepository.save(student);
}

public Student get(Long id){
    return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Could find student"));
}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findStudentByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found in database!"));
    }


    public Student addGrade(Long studentId, Long subjectId, Long pointId){

    Student student = studentRepository.findById(studentId).orElseThrow();

        SClass studentClass = classRepository.findSClassByStudentListContains(student);
        SchoolSubject studentSubject = studentClass.getSubjects().stream()
                .filter(a -> a.getId().equals(subjectId)).findAny()
                .orElseThrow(() -> new RuntimeException("Could find class"));

        List<Long> subjectGradesId = studentSubject.getGrades().stream().map(g -> g.getId()).collect(Collectors.toList());

        for (Long grades: subjectGradesId) {
            if( student.getGrades().stream().anyMatch(a -> a.getId().equals(grades))) {
                gradeService.addPointToGrade(grades,pointId);
            }
        }

        return student;
    }



}
