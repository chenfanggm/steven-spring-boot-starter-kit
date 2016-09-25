package com.weshare.wesharespring.entity.Request;

import javax.validation.constraints.NotNull;

public class RegisterRequest {

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Boolean rememberMe = false;

    public RegisterRequest() {
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

    public String getFirstName() { return firstName; }

    public void setFirstName(final String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(final String lastName) { this.lastName = lastName; }

    public Boolean getRememberMe() { return rememberMe; }

    public void setRememberMe(final Boolean rememberMe) { this.rememberMe = rememberMe; }
}
