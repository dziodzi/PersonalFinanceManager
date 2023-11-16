package ru.rfma.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequestReg {

    private String login;
    private String email;
    private char[] password;
    private String name;

}