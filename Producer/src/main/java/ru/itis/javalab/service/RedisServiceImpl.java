package ru.itis.javalab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.entity.RedisUser;
import ru.itis.javalab.entity.Tokens;
import ru.itis.javalab.entity.User;
import ru.itis.javalab.repository.UserRedisRepository;
import ru.itis.javalab.repository.UserRepository;

import java.util.*;

@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedisRepository userRedisRepository;

    @Override
    public void saveTokens(Tokens tokens, User user) {
        RedisUser redisUser = RedisUser.builder()
                .userId(user.getId())
                .refreshToken(tokens.getRefreshToken())
                .accessToken(tokens.getAccessToken())
                .expiredTimeRefreshToken(tokens.getExpiredTimeRefreshToken())
                .build();
        userRedisRepository.save(redisUser);
    }

    @Override
    public void addAllTokensToBlacklist(User user) {
        Optional<RedisUser> tokens = userRedisRepository.findById(String.valueOf(user.getId()));
        while(tokens.isPresent()){
            userRedisRepository.delete(tokens);
            tokens = userRedisRepository.findById(String.valueOf(user.getId()));
        }

    }

    @Override
    public Optional<User> deleteTokensAndReturnUser(String refreshToken) {
        Optional<RedisUser> redisUser = userRedisRepository.findByRefreshToken(refreshToken);
        if (redisUser.isPresent()){
            Optional<User> user = userRepository.findById(redisUser.get().getUserId());
            userRedisRepository.delete(redisUser.get());
            return user;
        }
        return Optional.empty();
    }

    @Override
    public Tokens findByRefreshToken(String refreshToken) {
        Optional<RedisUser> redisUser = userRedisRepository.findByRefreshToken(refreshToken);
        if (redisUser.isPresent()){
            return Tokens.builder()
                    .accessToken(redisUser.get().getAccessToken())
                    .refreshToken(redisUser.get().getRefreshToken())
                    .expiredTimeRefreshToken(redisUser.get().getExpiredTimeRefreshToken())
                    .build();
        }
        return null;
    }

    @Override
    public Optional<User> findUserByRefreshToken(String refreshToken) {
        Optional<RedisUser> redisUser = userRedisRepository.findByRefreshToken(refreshToken);
        if (redisUser.isPresent()){
            return userRepository.findById(redisUser.get().getUserId());
        }
        return Optional.empty();
    }
}
