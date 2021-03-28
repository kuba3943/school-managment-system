package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.repository.SchoolSubjectRepository;

@Service
@AllArgsConstructor
public class SubjectService {

    private final SchoolSubjectRepository schoolSubjectRepository;

    public SchoolSubject get(Long id){
        return schoolSubjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not find"));
    }
}
