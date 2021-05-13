package pl.schoolmanagementsystem.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanagementsystem.model.UserRole;
import pl.schoolmanagementsystem.service.StudentService;
import pl.schoolmanagementsystem.service.TeacherService;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @GetMapping
    public String login(){
        return "Authenticated succesfully";
    }

    @GetMapping("/role/{username}")
    public ResponseEntity<String> getRoleByUsername(@PathVariable String username){

        String role = "";

        if(studentService.findByUsername(username).isPresent()){
            role = studentService.findByUsername(username).get().getUserRole().name();
        } else if(teacherService.findByUsername(username).isPresent()){
            role = teacherService.findByUsername(username).get().getUserRole().name();
        }

       return ResponseEntity.ok(role);

    }


}
