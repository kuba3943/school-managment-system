package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.Point;
import pl.schoolmanagementsystem.repository.PointRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    public Point get(Long id){
        return pointRepository.findById(id).orElseThrow();
    }

    public List<Point> getAll(){
        return pointRepository.findAll();
    }

}
