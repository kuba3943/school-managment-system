
package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.repository.ClassRepository;
import pl.schoolmanagementsystem.repository.SchoolSubjectRepository;
import pl.schoolmanagementsystem.repository.StudentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final SchoolSubjectRepository subjectRepository;

    public SClass get(Long id){
        return classRepository.findById(id).orElseThrow(() -> new RuntimeException("Could find class"));
    }

    public SchoolSubject getSubject(Long classId, Long subjectId){
        return classRepository.findById(classId).orElseThrow().getSubjects().stream()
                .filter(a -> a.getId().equals(subjectId)).findAny()
                .orElseThrow(() -> new RuntimeException("Could find class"));
    }

    public List<SchoolSubject> getSubjectList (Long classId){
        return classRepository.findById(classId).orElseThrow().getSubjects();
    }

    public List<Student> getStudentList (Long classId){
        return classRepository.findById(classId).orElseThrow().getStudentList();
    }

    public boolean addStudentToClass (Long classId, Long studentId){
        return classRepository.findById(classId).orElseThrow().getStudentList()
                .add(studentRepository.findById(studentId).orElseThrow());
    }

    public boolean deleteStudentFromClass (Long classId, Long studentId){
        return classRepository.findById(classId).orElseThrow().getStudentList()
                .remove(studentRepository.findById(studentId).orElseThrow());
    }

    public boolean addSubjectToClass (Long classId, Long subjectId){
        return classRepository.findById(classId).orElseThrow().getSubjects()
                .add(subjectRepository.findById(subjectId).orElseThrow());
    }

    public boolean deleteSubjectFromClass (Long classId, Long subjectId){
        return classRepository.findById(classId).orElseThrow().getSubjects()
                .remove(subjectRepository.findById(subjectId).orElseThrow());
    }



}
