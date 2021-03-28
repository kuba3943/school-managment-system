package pl.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.Student;

public interface ClassRepository extends JpaRepository<SClass, Long> {

    SClass findSClassByStudentListContains(Student student);
}
