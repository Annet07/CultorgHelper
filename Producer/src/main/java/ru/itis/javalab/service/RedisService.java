package ru.itis.javalab.service;

import ru.itis.javalab.entity.Tokens;
import ru.itis.javalab.entity.User;

import java.util.Optional;

public interface RedisService {
    void saveTokens(Tokens tokens, User user);
    void addAllTokensToBlacklist(User user);
    Optional<User> deleteTokensAndReturnUser(String refreshToken);

    Tokens findByRefreshToken(String refreshToken);

    Optional<User> findUserByRefreshToken(String refreshToken);
}
