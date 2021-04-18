package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.model.Teacher;
import pl.schoolmanagementsystem.service.ClassService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/class")
@CrossOrigin(origins = "*")
public class ClassController {

    private final ClassService classService;

    @GetMapping("/{classId}/subject/{subjectId}")
    private ResponseEntity<SchoolSubject> getSubject(@PathVariable Long classId, @PathVariable Long subjectId) {
        return ResponseEntity.ok(classService.getSubject(classId, subjectId));
    }

    @GetMapping
    private  ResponseEntity<List<SClass>> getClasses (){
        return ResponseEntity.ok(classService.getAll());
    }


    @PostMapping
    public ResponseEntity<SClass> add(@RequestBody SClass sClass) {
        return ResponseEntity.ok(classService.add(sClass));
    }

    @PatchMapping
    public ResponseEntity<SClass> update(@RequestBody SClass sClass) {
        return ResponseEntity.ok(classService.update(sClass));
    }

    @DeleteMapping("/{classId}")
    public void delete(@PathVariable Long classId) {
        classService.delete(classId);
    }


    @GetMapping("/{classId}")
    private ResponseEntity<SClass> getClass(@PathVariable Long classId) {
        return ResponseEntity.ok(classService.get(classId));
    }

    @GetMapping("/{classId}/subjects")
    private ResponseEntity<List<SchoolSubject>> getSubjects(@PathVariable Long classId) {
        return ResponseEntity.ok(classService.getSubjectList(classId));
    }

    @GetMapping("/{classId}/students")
    private ResponseEntity<List<Student>> getStudentList(@PathVariable Long classId) {
        return ResponseEntity.ok(classService.getStudentList(classId));
    }

    @PatchMapping("{classId}/addStudent/{studentId}")
    private void addStudentToClass(@PathVariable Long classId, @PathVariable Long studentId) {
        classService.addStudentToClass(classId, studentId);
    }

    @PatchMapping("{classId}/deleteStudent/{studentId}")
    private ResponseEntity<Boolean> deleteStudentFromClass(@PathVariable Long classId, @PathVariable Long studentId) {
        return ResponseEntity.ok(classService.deleteStudentFromClass(classId, studentId));
    }

    @PatchMapping("{classId}/addSubject")
    private ResponseEntity<SClass> addSubjectToClass(@PathVariable Long classId, @RequestBody SchoolSubject subject) {
        return ResponseEntity.ok(classService.addSubjectToClass(classId, subject));
    }

    @PatchMapping("{classId}/deleteSubject/{subjectId}")
    private ResponseEntity<Boolean> deleteSubjectFromClass(@PathVariable Long classId, @PathVariable Long subjectId) {
        return ResponseEntity.ok(classService.deleteSubjectFromClass(classId, subjectId));
    }


}
