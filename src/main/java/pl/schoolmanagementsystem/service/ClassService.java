
package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.SClass;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.model.Student;
import pl.schoolmanagementsystem.model.Teacher;
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

    public List<SClass> getAll(){
        return classRepository.findAll();
    }

    public SClass add(SClass sClass){
        return classRepository.save(sClass);
    }

    public SClass update(SClass sClass){
        return classRepository.save(sClass);
    }

    public void delete(Long classId){
        classRepository.delete(classRepository.findById(classId).orElseThrow());
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

    public void addStudentToClass (Long classId, Long studentId){

        SClass sclass = classRepository.findById(classId).orElseThrow();
        sclass.getStudentList().add(studentRepository.findById(studentId).orElseThrow());

        classRepository.save(sclass);
    }

    public boolean deleteStudentFromClass (Long classId, Long studentId){
        return classRepository.findById(classId).orElseThrow().getStudentList()
                .remove(studentRepository.findById(studentId).orElseThrow());
    }

    public SClass addSubjectToClass (Long classId, SchoolSubject subject){
        SClass sClass = classRepository.findById(classId).orElseThrow();
        SchoolSubject schoolSubject = subjectRepository.save(subject);
        sClass.getSubjects().add(schoolSubject);
        return classRepository.save(sClass);
    }

    public boolean deleteSubjectFromClass (Long classId, Long subjectId){
        return classRepository.findById(classId).orElseThrow().getSubjects()
                .remove(subjectRepository.findById(subjectId).orElseThrow());
    }



}
