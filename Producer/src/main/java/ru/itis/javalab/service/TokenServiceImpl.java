package ru.itis.javalab.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.entity.RefreshToken;
import ru.itis.javalab.entity.Response;
import ru.itis.javalab.entity.User;
import ru.itis.javalab.repository.TokenRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService{

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access.lifetime}")
    private Long accessLifeTime;

    @Value("${token.refresh.lifetime}")
    private Long refreshLifetime;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public TokenDto getNewTokens(User user) {
        String accessToken = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("kind", user.getRole().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+accessLifeTime))
                .sign(Algorithm.HMAC256(secretKey));
        String refreshTokenToString = UUID.randomUUID().toString();
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(refreshTokenToString)
                .user(user)
                .expiredTime(new Date(System.currentTimeMillis()+refreshLifetime))
                .build();
        Optional<RefreshToken> oldRefreshToken = tokenRepository.findByUser(user);
        oldRefreshToken.ifPresent(value -> tokenRepository.delete(value));
        tokenRepository.save(refreshToken);
        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshTokenToString).build();

    }

    @Override
    public Response updateTokenWithRefresh(String token) {
        Optional<RefreshToken> refreshToken = tokenRepository.findByRefreshToken(token);
        if (!refreshToken.isPresent()){
            return Response.builder()
                    .success(false)
                    .response("Данный Refresh Token не найден!")
                    .build();
        }
        return Response.builder()
                .success(true)
                .response(getNewTokens(refreshToken.get().getUser()))
                .build();
    }

    @Override
    public Optional<RefreshToken> getRefreshToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken);
    }
}
