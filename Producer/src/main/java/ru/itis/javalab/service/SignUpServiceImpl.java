package ru.itis.javalab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.entity.Response;
import ru.itis.javalab.entity.User;
import ru.itis.javalab.form.SignUpForm;

@Service
public class SignUpServiceImpl implements SignUpService{

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Response signUp(SignUpForm signUpForm) {
        User.Role role;
        if (userService.getUserByEmail(signUpForm.getEmail()) != null) {
            return Response.builder()
                    .success(false)
                    .response("Email, который вы ввели, на сайте уже используется. Введите другой email!")
                    .build();
        }
        if (signUpForm.getPassword().compareTo(signUpForm.getRepeatedPassword()) == 0) {
            if (signUpForm.getRole().compareTo("USER") == 0){
                role = User.Role.USER;
            } else {
                role = User.Role.ADMIN;
            }
            User user = User.builder()
                    .email(signUpForm.getEmail())
                    .hashPassword(passwordEncoder.encode(signUpForm.getPassword()))
                    .role(role)
                    .name(signUpForm.getName())
                    .surname(signUpForm.getSurname())
                    .institute(signUpForm.getInstitute())
                    .build();
            userService.save(user);
            return Response.builder()
                    .success(true)
                    .response("Пользователь с адресом электронной почты " +
                            signUpForm.getEmail() +
                            " успешно зарегистрирован!")
                    .build();
        }
        return Response.builder()
                .success(false)
                .response("Введенные пароли не совпадают. Пожалуйста, введите заново!")
                .build();
    }


}
