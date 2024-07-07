package br.edu.catolicasc.flowyservices.dto;

public class LoginRequest {
    private String userName;
    private String userPassword;
    private String userEmail;

    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}