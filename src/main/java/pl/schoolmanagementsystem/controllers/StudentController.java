package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanagementsystem.model.Grades;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.service.StudentService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> get(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.get(studentId));
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody @Valid Student student) {

        if (studentService.findByUsername(student.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is alredy taken");
        } else {
            String password = new BCryptPasswordEncoder().encode(student.getPassword());
            student.setPassword(password);
            return ResponseEntity.ok(studentService.add(student));
        }
    }

    @PostMapping("/{studentId}/subject/{subjectId}/point/{pointId}")
    public ResponseEntity<Student> addGrade(@PathVariable Long studentId, @PathVariable Long subjectId, @PathVariable Long pointId) {
        return ResponseEntity.ok(studentService.addGrade(studentId, subjectId, pointId));
    }

    @PatchMapping
    public ResponseEntity<Student> update(@RequestBody @Valid Student student) {
        String password = new BCryptPasswordEncoder().encode(student.getPassword());
        student.setPassword(password);
        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("/{studentId}")
    public void delete (@PathVariable Long studentId){
        studentService.delete(studentId);
    }

    @GetMapping("/{studentId}/subject/{subjectId}")
    public ResponseEntity<Grades> getGradesBySubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return ResponseEntity.ok(studentService.getStudentGradesBySubject(studentId, subjectId));
    }

    @DeleteMapping("/{studentId}/subject/{subjectId}/point/{pointId}")
    public ResponseEntity<Student> deleteGrade(@PathVariable Long studentId, @PathVariable Long subjectId, @PathVariable Long pointId) {
        return ResponseEntity.ok(studentService.deleteGrade(studentId, subjectId, pointId));
    }


}
