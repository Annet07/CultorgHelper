package ru.itis.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.entity.RefreshToken;
import ru.itis.javalab.entity.User;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String token);
    void delete(RefreshToken refreshToken);
    Optional<RefreshToken> findByUser(User user);
}
