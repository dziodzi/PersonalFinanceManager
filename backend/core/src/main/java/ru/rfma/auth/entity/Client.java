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

    @Enumerated(EnumType.STRING)
    private Role role;

    public Client(String login, String password, Role role) {
        this.login = login;
        this.password = password.toCharArray();
        this.role = role;
    }

    public void setAdmin(){
        this.role = Role.ADMIN;
    }
}