package ru.itis.javalab.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.entity.RedisUser;
import ru.itis.javalab.repository.UserRedisRepository;

import java.util.Optional;

@Service
public class JwtBlacklistServiceImpl implements JwtBlacklistService{

    @Autowired
    private UserRedisRepository userRedisRepository;

    @Override
    public void deleteToken(String accessToken) {
        Optional<RedisUser> redisUser = userRedisRepository.findByAccessToken(accessToken);
        redisUser.ifPresent(user -> userRedisRepository.delete(user));

    }

    @Override
    public boolean existsAccessToken(String accessToken) {
        return userRedisRepository.findByAccessToken(accessToken).isPresent();
    }

    @Override
    public boolean existsRefreshToken(String refreshToken) {
        return userRedisRepository.findByRefreshToken(refreshToken).isPresent();
    }
}
