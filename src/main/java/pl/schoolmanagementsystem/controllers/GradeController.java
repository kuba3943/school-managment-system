package pl.schoolmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.schoolmanagementsystem.model.Grades;
import pl.schoolmanagementsystem.service.GradeService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/grade")
public class GradeController {

    private final GradeService gradeService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Grades> get(@PathVariable Long id){
//        return ResponseEntity.ok(gradeService.get(id));
//    }

}
