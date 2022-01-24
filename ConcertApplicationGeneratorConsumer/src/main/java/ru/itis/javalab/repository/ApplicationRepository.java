package ru.itis.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
