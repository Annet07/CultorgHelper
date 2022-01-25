package ru.itis.javalab.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.javalab.entity.Tokens;
import ru.itis.javalab.entity.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.service.TokenService;

import java.util.Date;
import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenService tokenService;

    @Value("${token.secret.key}")
    private String secretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        UserDetails userDetails;
        try{
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(tokenAuthentication.getName());
            userDetails = new UserDetailsImpl(User.builder()
                    .email(decodedJWT.getClaim("email").asString())
                    .role(User.Role.valueOf(decodedJWT.getClaim("kind").asString()))
                    .build());
        } catch (JWTVerificationException e){
            throw new IllegalAccessError(e.getMessage());
        }
        String refreshToken = tokenAuthentication.getRefreshToken();
        if (refreshToken != null){
            Tokens token = tokenService.getRefreshToken(refreshToken);
            Optional<User> user = tokenService.getUserByRefreshToken(refreshToken);
            if (!(token != null && user.isPresent() && user.get().getEmail().equals(userDetails.getUsername())
                    && token.getExpiredTimeRefreshToken().compareTo(new Date()) > 0)){
                throw new IllegalAccessError("Refresh Token устарел. Войдите в системы заново, чтобы обновить токены!");
            }
        }
        authentication.setAuthenticated(true);
        tokenAuthentication.setUserDetails(userDetails);
        return tokenAuthentication;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
