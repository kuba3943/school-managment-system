package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.service.StudentService;

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
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.add(student));
    }

    @PostMapping("/{studentId}/{subjectId}/{pointId}")
    public ResponseEntity<Student> addGrade(@PathVariable Long studentId, @PathVariable Long subjectId, @PathVariable Long pointId) {
        return ResponseEntity.ok(studentService.addGrade(studentId, subjectId, pointId));
    }


}
