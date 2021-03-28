package pl.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.schoolmanagementsystem.model.HeadMaster;

import java.util.Optional;

public interface HeadMasterRepository extends JpaRepository<HeadMaster, Long> {


    Optional<HeadMaster> findHeadMasterByUsername(String username);
}
