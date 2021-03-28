package pl.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.schoolmanagementsystem.model.Point;

public interface PointRepository  extends JpaRepository<Point, Long> {


}
