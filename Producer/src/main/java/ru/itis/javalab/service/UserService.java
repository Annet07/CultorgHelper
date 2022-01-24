package ru.itis.javalab.service;

import ru.itis.javalab.entity.User;

public interface UserService {
    User getUserByEmail(String email);
    void save(User user);
    User getUserById(Long code);
    void delete(User oldUser);
}
