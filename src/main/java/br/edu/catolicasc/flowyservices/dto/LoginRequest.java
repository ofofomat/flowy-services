package br.edu.catolicasc.flowyservices.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String userName;
    private String userPassword;
    private String userEmail;
}
