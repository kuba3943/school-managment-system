package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanagementsystem.dto.StudentDto;
import pl.schoolmanagementsystem.model.Grades;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }


    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> get(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.get(studentId));
    }

    @GetMapping("/{studentId}/class")
    public ResponseEntity<Boolean> getStudentClass(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentClass(studentId));
    }

    @GetMapping("/noClass")
    public ResponseEntity<List<StudentDto>> getStudentsWithNoClass(){
        return ResponseEntity.ok(studentService.getStudentsWithNoClass());
    }

    @PostMapping
    public ResponseEntity<StudentDto> save(@RequestBody @Valid Student student) {

        if (studentService.findByUsername(student.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is alredy taken");
        } else {
            String password = new BCryptPasswordEncoder().encode(student.getPassword());
            student.setPassword(password);
            return ResponseEntity.ok(studentService.add(student));
        }
    }

    @PostMapping("/{studentId}/subject/{subjectId}/point/{pointId}")
    public ResponseEntity<StudentDto> addGrade(@PathVariable Long studentId, @PathVariable Long subjectId, @PathVariable Long pointId) {
        return ResponseEntity.ok(studentService.addGrade(studentId, subjectId, pointId));
    }

    @PatchMapping
    public ResponseEntity<StudentDto> update(@RequestBody @Valid Student student) {
        String password = new BCryptPasswordEncoder().encode(student.getPassword());
        student.setPassword(password);
        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<StudentDto> delete (@PathVariable Long studentId){
        return ResponseEntity.ok(studentService.delete(studentId));
    }

    @GetMapping("/{studentId}/subject/{subjectId}")
    public ResponseEntity<Grades> getGradesBySubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return ResponseEntity.ok(studentService.getStudentGradesBySubject(studentId, subjectId));
    }

    @DeleteMapping("/{studentId}/subject/{subjectId}/point/{pointId}")
    public ResponseEntity<StudentDto> deleteGrade(@PathVariable Long studentId, @PathVariable Long subjectId, @PathVariable Long pointId) {
        return ResponseEntity.ok(studentService.deleteGrade(studentId, subjectId, pointId));
    }

    @GetMapping("/{studentId}/subjects")
        public ResponseEntity<List<SchoolSubject>> getStudentsSubjects (@PathVariable Long studentId){
            return ResponseEntity.ok(studentService.getStudentsSubject(studentId));
        }

    @GetMapping("/username/{username}")
    public ResponseEntity<StudentDto> getStudentByUsername(@PathVariable String username) {
        return ResponseEntity.ok(studentService.findStudentDto(username));
    }

}
