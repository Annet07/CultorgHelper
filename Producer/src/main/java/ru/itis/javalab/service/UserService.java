package ru.itis.javalab.service;

import ru.itis.javalab.entity.User;

public interface UserService {
    void blockUser(Long userId);
    User getUserByEmail(String email);
    void save(User user);
    User getUserById(Long code);
    void delete(User oldUser);
}
