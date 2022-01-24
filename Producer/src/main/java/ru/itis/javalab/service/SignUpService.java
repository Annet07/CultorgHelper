package ru.itis.javalab.service;

import ru.itis.javalab.entity.Response;
import ru.itis.javalab.form.SignInForm;
import ru.itis.javalab.form.SignUpForm;

public interface SignUpService {
    Response signUp(SignUpForm signUpForm);
}
