package pl.schoolmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.schoolmanagementsystem.model.Grades;
import pl.schoolmanagementsystem.model.UserRole;
import pl.schoolmanagementsystem.validation.PasswordAnnotation;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

    private Long id;
    private String password;
    private String username;
    private String name;
    private String surname;
    private LocalDate DOB;
    private List<Grades> grades;
    private UserRole userRole;

}
