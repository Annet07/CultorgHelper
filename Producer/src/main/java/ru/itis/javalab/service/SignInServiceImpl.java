package ru.itis.javalab.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.entity.Response;
import ru.itis.javalab.entity.User;
import ru.itis.javalab.form.SignInForm;

@Service
public class SignInServiceImpl implements SignInService{

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public Response signIn(SignInForm signInForm) {
        User user = userService.getUserByEmail(signInForm.getEmail());
        if (user == null){
            return Response.builder()
                    .success(false)
                    .response("Пользователь с таким email не найден!")
                    .build();
        }
        if (passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword())){
            TokenDto tokens = tokenService.getNewTokens(user);
            return Response.builder()
                    .success(true)
                    .response(tokens)
                    .build();
        }
        return Response.builder()
                .success(false)
                .response("Неверный пароль")
                .build();
    }
}
