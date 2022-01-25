package ru.itis.javalab.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("redis_user")
public class RedisUser {

    @Id
    private String id;
    @Indexed
    private String accessToken;
    @Indexed
    private String refreshToken;
    private Date expiredTimeRefreshToken;
    @Indexed
    private Long userId;
}
