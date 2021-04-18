package pl.schoolmanagementsystem.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {


    @GetMapping
    public String login(){
        return "Authenticated succesfully";
    }


}
