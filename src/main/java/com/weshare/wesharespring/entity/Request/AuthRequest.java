package com.weshare.wesharespring.entity.Request;

import javax.validation.constraints.NotNull;

public class AuthRequest {

    @NotNull
    private String email;
    @NotNull
    private String password;
    private String username;
    private Boolean rememberMe = false;

    public AuthRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(final String username) { this.username = username; }

    public Boolean getRememberMe() { return rememberMe; }

    public void setRememberMe(final Boolean rememberMe) { this.rememberMe = rememberMe; }
}
