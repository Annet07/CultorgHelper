package ru.itis.javalab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.entity.User;
import ru.itis.javalab.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        Optional<User> opUser = userRepository.findByEmail(email);
        return opUser.map(user -> User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .institute(user.getInstitute())
                .hashPassword(user.getHashPassword())
                .role(user.getRole())
                .id(user.getId())
                .build()).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long code) {
        return userRepository.findById(code).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(User oldUser) {
        userRepository.delete(oldUser);
    }
}
