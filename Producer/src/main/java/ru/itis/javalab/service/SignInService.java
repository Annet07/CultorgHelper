package ru.itis.javalab.service;

import ru.itis.javalab.entity.Response;
import ru.itis.javalab.form.SignInForm;

public interface SignInService {
    Response signIn(SignInForm signInForm);
}
