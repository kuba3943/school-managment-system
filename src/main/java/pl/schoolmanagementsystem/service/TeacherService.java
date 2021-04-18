package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.SchoolSubject;
import pl.schoolmanagementsystem.model.Teacher;
import pl.schoolmanagementsystem.repository.SchoolSubjectRepository;
import pl.schoolmanagementsystem.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService implements UserDetailsService {

    private final TeacherRepository teacherRepository;
    private final SchoolSubjectRepository subjectRepository;


    public Teacher get(Long id){
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Could find teacher"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teacherRepository.findTeacherByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found in database!"));
    }

    public Teacher add(Teacher teacher){
         return teacherRepository.save(teacher);
    }

    public Teacher update(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public void delete(Long teacherId){
        teacherRepository.delete(teacherRepository.findById(teacherId).orElseThrow());
    }

    public Optional<Teacher> findByUsername(String username) {
        return teacherRepository.findTeacherByUsername(username);
    }

    public List<SchoolSubject> getSubjectList(Long teacherId){

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();

        return teacher.getSubjectList();

    }

    public boolean addSubjectToTeacher(Long teacherId, Long subjectId){
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        SchoolSubject subject = subjectRepository.findById(subjectId).orElseThrow();

        return teacher.getSubjectList().add(subject);
    }

    public boolean deleteSubjectFromTeacher(Long teacherId, Long subjectId){
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        SchoolSubject subject = subjectRepository.findById(subjectId).orElseThrow();

        return teacher.getSubjectList().remove(subject);
    }



}
