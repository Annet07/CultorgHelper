package ru.itis.javalab.service;





import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.entity.Response;
import ru.itis.javalab.entity.Tokens;
import ru.itis.javalab.entity.User;

import java.util.Optional;

public interface TokenService {
    TokenDto getNewTokens(User user);
    Response updateTokenWithRefresh(String token);
    Tokens getRefreshToken(String refreshToken);

    Optional<User> getUserByRefreshToken(String refreshToken);
}
