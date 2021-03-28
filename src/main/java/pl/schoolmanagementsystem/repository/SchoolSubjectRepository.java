package pl.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.schoolmanagementsystem.model.SchoolSubject;

public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Long> {
}
