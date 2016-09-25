package com.weshare.wesharespring.entity.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weshare.wesharespring.entity.Token;

public class AuthResponse {

    @JsonProperty
    private String token;
    @JsonProperty
    private String refreshToken;
    @JsonProperty
    private UserResponse user;

    public AuthResponse() { }

    public AuthResponse(final Token token, final UserResponse user) {
        this.token = token.getToken();
        if (token.hasRefreshToken()) {
            this.refreshToken = token.getRefreshToken();
        }
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public String getRefreshToken() { return refreshToken; }

    public void setRefreshToken(final String refreshToken) { this.refreshToken = refreshToken; }

    public UserResponse getUser() { return user; }

    public void setUser(final UserResponse user) { this.user = user; }
}
