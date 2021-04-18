package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanagementsystem.model.SClass;
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

    @PostMapping
    public ResponseEntity<SchoolSubject> add(@RequestBody SchoolSubject subject) {
        return ResponseEntity.ok(subjectService.add(subject));
    }

    @PatchMapping
    public ResponseEntity<SchoolSubject> update(@RequestBody SchoolSubject subject) {
        return ResponseEntity.ok(subjectService.update(subject));
    }

    @DeleteMapping("/{subjectId}")
    public void delete(@PathVariable Long subjectId) {
        subjectService.delete(subjectId);
    }


}
