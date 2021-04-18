package pl.schoolmanagementsystem.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.schoolmanagementsystem.dto.StudentDto;
import pl.schoolmanagementsystem.model.Point;
import pl.schoolmanagementsystem.service.PointService;
import pl.schoolmanagementsystem.service.StudentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/points")
@CrossOrigin(origins = "*")
public class PointController {

    private final PointService pointService;

    @GetMapping
    public ResponseEntity<List<Point>> getAll() {
        return ResponseEntity.ok(pointService.getAll());
    }





}
