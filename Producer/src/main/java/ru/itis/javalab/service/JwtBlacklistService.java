package ru.itis.javalab.service;

public interface JwtBlacklistService {

    void deleteToken(String accessToken);
    boolean existsAccessToken(String accessToken);
    boolean existsRefreshToken(String refreshToken);
}
