package pl.schoolmanagementsystem.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.model.Teacher;
import pl.schoolmanagementsystem.service.TeacherService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> get(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherService.get(teacherId));
    }

    @PostMapping()
    public ResponseEntity<Teacher> add(@RequestBody @Valid Teacher teacher){
        if (teacherService.findByUsername(teacher.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is alredy taken");
        } else {
            String password = new BCryptPasswordEncoder().encode(teacher.getPassword());
            teacher.setPassword(password);
            return ResponseEntity.ok(teacherService.add(teacher));
        }
    }

    @PatchMapping
    public ResponseEntity<Teacher> update(@RequestBody @Valid Teacher teacher) {
        String password = new BCryptPasswordEncoder().encode(teacher.getPassword());
        teacher.setPassword(password);
        return ResponseEntity.ok(teacherService.update(teacher));
    }

    @DeleteMapping("/{teacherId}")
    public void delete (@PathVariable Long teacherId){
        teacherService.delete(teacherId);
    }

    @GetMapping("/{teacherId}/subjects")
    public ResponseEntity<List<SchoolSubject>> getSubjects(@PathVariable Long teacherId){
        return ResponseEntity.ok(teacherService.getSubjectList(teacherId));
    }

    @PatchMapping("/{teacherId}/addSubject/{subjectId}")
    public ResponseEntity<Boolean> addSubjectToTeacher(@PathVariable Long teacherId, @PathVariable Long subjectId){
        return ResponseEntity.ok(teacherService.addSubjectToTeacher(teacherId, subjectId));
    }

    @PatchMapping("/{teacherId}/deleteSubject/{subjectId}")
    public ResponseEntity<Boolean> deleteSubjectFromTeacher(@PathVariable Long teacherId, @PathVariable Long subjectId){
        return ResponseEntity.ok(teacherService.deleteSubjectFromTeacher(teacherId, subjectId));
    }





}
