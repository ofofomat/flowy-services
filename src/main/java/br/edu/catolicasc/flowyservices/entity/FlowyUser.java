package br.edu.catolicasc.flowyservices.entity;

import jakarta.persistence.*;

@Entity
public class FlowyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String userName;

    @Column(length = 50, nullable = false)
    private String userEmail;

    @Column(length = 50, nullable = false)
    private String userPassword;

    // Getters e Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
