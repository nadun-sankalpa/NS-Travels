package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AuthTokenDTO {
    private boolean authenticated;
    private String token;
    private String message;

    public AuthTokenDTO() {
    }

    public AuthTokenDTO(boolean authenticated, String token, String message) {
        this.authenticated = authenticated;
        this.token = token;
        this.message = message;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthTokenDTO{" +
                "authenticated=" + authenticated +
                ", token='" + token + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
