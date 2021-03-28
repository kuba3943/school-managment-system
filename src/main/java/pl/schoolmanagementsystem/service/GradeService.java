package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.Grades;
import pl.schoolmanagementsystem.model.Point;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.repository.GradesRepository;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradesRepository gradesRepository;
    private final PointService pointService;

    public Grades get(Long id){
        return gradesRepository.findById(id).orElseThrow(() -> new RuntimeException("Could find student"));
    }




    public Grades addGrade(Grades grades){
        return gradesRepository.save(grades);
    }

    public Grades addPointToGrade (Long id, Long pointId){

        Grades grades = gradesRepository.findById(id).orElseThrow();
        Point point = pointService.get(pointId);


        grades.getPoints().add(point);

        gradesRepository.save(grades);
        return grades;
    }





}
