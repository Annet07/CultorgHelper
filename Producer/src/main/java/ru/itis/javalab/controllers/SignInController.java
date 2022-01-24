package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.entity.Response;
import ru.itis.javalab.form.SignInForm;
import ru.itis.javalab.service.SignInService;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/sign-in")
    public ResponseEntity<Response> signIn(@RequestBody SignInForm signInForm){
        return ResponseEntity.ok(signInService.signIn(signInForm));
    }
}
