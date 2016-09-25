package com.weshare.wesharespring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token implements Serializable {

    private String token;
    private String refreshToken;
    private Long userId;
    private String device;
    private String ip;
    private Long timeCreated;
    private Long timeUpdated;

    public Token(String token) {
        this.token = token;
    }

    public Token(Long userId, String device, String ip, String refreshToken, Long timeCreated, Long timeUpdated) {
        this.userId = userId;
        this.device = device;
        this.ip = ip;
        this.refreshToken = refreshToken;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
    }

    public Boolean hasRefreshToken() {
        return refreshToken != null && !refreshToken.equals("");
    }
}
