package ru.rfma.auth.entity;

import lombok.*;
import ru.rfma.auth.enums.Role;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client {
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    private String login;
    private char[] password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Client(String login, String password, Role role, String email) {
        this.login = login;
        this.password = password.toCharArray();
        this.role = role;
        this.email = email;
    }

    public void setAdmin(){
        this.role = Role.ADMIN;
    }
}