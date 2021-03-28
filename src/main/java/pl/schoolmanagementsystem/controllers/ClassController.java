package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.service.ClassService;

@RestController
@AllArgsConstructor
@RequestMapping("api/class")
public class ClassController {

    private final ClassService classService;

    @GetMapping("/{classId}/{subjectId}")
    private ResponseEntity<SchoolSubject> getSubject(@PathVariable Long classId, @PathVariable Long subjectId){
        return ResponseEntity.ok(classService.getSubject(classId,subjectId));
    }

}
