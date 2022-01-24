package ru.itis.javalab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String surname;
    private String institute;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role{
        USER, ADMIN;
    }

}
