
package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.repository.ClassRepository;

@Service
@AllArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;

    public SClass get(Long id){
        return classRepository.findById(id).orElseThrow(() -> new RuntimeException("Could find class"));
    }

    public SchoolSubject getSubject(Long classId, Long subjectId){
        return classRepository.findById(classId).orElseThrow().getSubjects().stream()
                .filter(a -> a.getId().equals(subjectId)).findAny()
                .orElseThrow(() -> new RuntimeException("Could find class"));
    }


}
