package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.entity.Response;
import ru.itis.javalab.form.SignUpForm;
import ru.itis.javalab.service.SignUpService;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/sign-up")
    public ResponseEntity<Response> signUp(@RequestBody SignUpForm signUpForm){
        return ResponseEntity.ok(signUpService.signUp(signUpForm));
    }
}
