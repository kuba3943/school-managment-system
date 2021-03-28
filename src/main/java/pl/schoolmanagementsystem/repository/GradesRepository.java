package pl.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.schoolmanagementsystem.model.Grades;

public interface GradesRepository extends JpaRepository<Grades, Long> {
}
