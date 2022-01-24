package ru.itis.javalab.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {

    private String email;
    private String name;
    private String surname;
    private String institute;
    private String password;
    private String repeatedPassword;
    private String role;
}
