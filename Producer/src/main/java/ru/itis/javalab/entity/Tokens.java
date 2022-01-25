package ru.itis.javalab.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tokens {

    private String accessToken;
    private String refreshToken;
    private Date expiredTimeRefreshToken;
}
