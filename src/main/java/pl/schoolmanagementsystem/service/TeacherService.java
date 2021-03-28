package pl.schoolmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.schoolmanagementsystem.model.Teacher;
import pl.schoolmanagementsystem.repository.TeacherRepository;

@Service
@AllArgsConstructor
public class TeacherService implements UserDetailsService {

    private final TeacherRepository teacherRepository;


    public Teacher get(Long id){
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Could find teacher"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teacherRepository.findTeacherByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found in database!"));
    }
}
