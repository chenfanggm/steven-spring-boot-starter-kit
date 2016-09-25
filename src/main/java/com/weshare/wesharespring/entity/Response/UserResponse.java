package com.weshare.wesharespring.entity.Response;

import com.weshare.wesharespring.entity.Profile;
import com.weshare.wesharespring.entity.User;

public class UserResponse {

    private String email;
    private String username;
    private Integer verified;
    private Integer status;
    private Long lastLogin;
    private Profile profile;

    public UserResponse() {
    }

    public UserResponse(final User user, final Profile profile) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.verified = user.getVerified();
        this.status = user.getStatus();
        this.lastLogin = user.getLastLogin();
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Profile getProfile() { return profile; }

    public void setProfile(final Profile profile) { this.profile = profile; }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(final Integer verified) {
        this.verified = verified;
    }

    public Integer getStatus() { return status; }

    public void setStatus(final Integer status) { this.status = status; }

    public Long getLastLogin() { return lastLogin; }

    public void setLastLogin(final Long lastLogin) { this.lastLogin = lastLogin; }
}
