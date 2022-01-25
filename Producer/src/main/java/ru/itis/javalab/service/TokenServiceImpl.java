package ru.itis.javalab.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.entity.Response;
import ru.itis.javalab.entity.Tokens;
import ru.itis.javalab.entity.User;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private RedisService redisService;

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access.lifetime}")
    private Long accessLifeTime;

    @Value("${token.refresh.lifetime}")
    private Long refreshLifetime;

    @Override
    public TokenDto getNewTokens(User user) {
        String accessToken = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("kind", user.getRole().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+accessLifeTime))
                .sign(Algorithm.HMAC256(secretKey));
        String refreshTokenToString = UUID.randomUUID().toString();
        Date expiredTimeRefreshToken = new Date(System.currentTimeMillis()+refreshLifetime);
        redisService.saveTokens(Tokens.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenToString)
                .expiredTimeRefreshToken(expiredTimeRefreshToken)
                .build(), user);
        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshTokenToString).build();

    }

    @Override
    public Response updateTokenWithRefresh(String token) {
        Optional<User> user = redisService.deleteTokensAndReturnUser(token);
        if (!user.isPresent()){
            return Response.builder()
                    .success(false)
                    .response("Данный Refresh Token не найден!")
                    .build();
        }
        return Response.builder()
                .success(true)
                .response(getNewTokens(User.builder()
                        .institute(user.get().getInstitute())
                        .role(user.get().getRole())
                        .surname(user.get().getSurname())
                        .name(user.get().getName())
                        .email(user.get().getEmail())
                        .hashPassword(user.get().getHashPassword())
                        .id(user.get().getId())
                        .build()))
                .build();
    }

    @Override
    public Tokens getRefreshToken(String refreshToken) {
        return redisService.findByRefreshToken(refreshToken);
    }

    @Override
    public Optional<User> getUserByRefreshToken(String refreshToken) {
        return redisService.findUserByRefreshToken(refreshToken);
    }
}
