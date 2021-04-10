package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.Grades;
import pl.schoolmanagementsystem.model.Point;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.repository.GradesRepository;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradesRepository gradesRepository;
    private final PointService pointService;

    public Grades get(Long id){
        return gradesRepository.findById(id).orElseThrow(() -> new RuntimeException("Could find student"));
    }

    public Grades delete(Long gradeId, Long pointId){
        Grades grades = gradesRepository.findById(gradeId).orElseThrow();
        grades.getPoints().remove(pointService.get(pointId));

        return gradesRepository.save(grades);

    }




    public Grades addGrade(Grades grades){
        return gradesRepository.save(grades);
    }

    public Grades addPointToGrade (Long id, Long pointId){

        Grades grades = gradesRepository.findById(id).orElseThrow();
        Point point = pointService.get(pointId);

        if (grades.getPoints()!=null){
            grades.getPoints().add(point);
        } else {
            grades.setPoints(new ArrayList<>());
            grades.getPoints().add(point);
        }

        gradesRepository.save(grades);
        return grades;
    }





}
