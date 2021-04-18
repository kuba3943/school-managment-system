package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.repository.SchoolSubjectRepository;

@Service
@AllArgsConstructor
public class SubjectService {

    private final SchoolSubjectRepository schoolSubjectRepository;

    public SchoolSubject get(Long id){
        return schoolSubjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not find"));
    }
    public SchoolSubject add(SchoolSubject subject){
        return schoolSubjectRepository.save(subject);
    }

    public SchoolSubject update(SchoolSubject subject){
        return schoolSubjectRepository.save(subject);
    }

    public void delete(Long subjectId){
        schoolSubjectRepository.delete(schoolSubjectRepository.findById(subjectId).orElseThrow());
    }
}
