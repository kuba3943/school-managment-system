package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.service.ClassService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/class")
public class ClassController {

    private final ClassService classService;

    @GetMapping("/{classId}/subject/{subjectId}")
    private ResponseEntity<SchoolSubject> getSubject(@PathVariable Long classId, @PathVariable Long subjectId){
        return ResponseEntity.ok(classService.getSubject(classId,subjectId));
    }

    @GetMapping("/{classId}")
    private ResponseEntity<SClass> getClass(@PathVariable Long classId){
        return ResponseEntity.ok(classService.get(classId));
    }

    @GetMapping("/{classId}/subjects")
    private ResponseEntity<List<SchoolSubject>> getSubjects(@PathVariable Long classId){
        return ResponseEntity.ok(classService.getSubjectList(classId));
    }

    @GetMapping("/{classId}/students")
    private ResponseEntity<List<Student>> getStudentList (@PathVariable Long classId){
        return ResponseEntity.ok(classService.getStudentList(classId));
    }

    @PatchMapping("{classId}/addStudent/{studentId}")
    private ResponseEntity<Boolean> addStudentToClass (@PathVariable Long classId, @PathVariable Long studentId){
        return ResponseEntity.ok(classService.addStudentToClass(classId,studentId));
    }

    @PatchMapping("{classId}/deleteStudent/{studentId}")
    private ResponseEntity<Boolean> deleteStudentFromClass (@PathVariable Long classId, @PathVariable Long studentId){
        return ResponseEntity.ok(classService.deleteStudentFromClass(classId,studentId));
    }

    @PatchMapping("{classId}/addSubject/{subjectId}")
    private ResponseEntity<Boolean> addSubjectToClass (@PathVariable Long classId, @PathVariable Long subjectId){
        return ResponseEntity.ok(classService.addSubjectToClass(classId,subjectId));
    }

    @PatchMapping("{classId}/deleteSubject/{subjectId}")
    private ResponseEntity<Boolean> deleteSubjectFromClass (@PathVariable Long classId, @PathVariable Long subjectId){
        return ResponseEntity.ok(classService.deleteSubjectFromClass(classId,subjectId));
    }


}
