package ru.itis.javalab.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.javalab.entity.RedisUser;


import java.util.Optional;

public interface UserRedisRepository extends KeyValueRepository<RedisUser, String> {
    void delete(Optional<RedisUser> tokens);
    Optional<RedisUser> findByRefreshToken(String refreshToken);
    Optional<RedisUser> findByAccessToken(String accessToken);
}
