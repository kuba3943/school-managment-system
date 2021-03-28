package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.service.SubjectService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/{subjectId}")
    public ResponseEntity<SchoolSubject> get(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.get(subjectId));
    }


}
