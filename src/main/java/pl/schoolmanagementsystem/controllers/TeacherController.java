package pl.schoolmanagementsystem.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.schoolmanagementsystem.model.Teacher;
import pl.schoolmanagementsystem.service.TeacherService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> get(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherService.get(teacherId));
    }

}
