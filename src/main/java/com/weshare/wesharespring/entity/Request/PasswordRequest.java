package com.weshare.wesharespring.entity.Request;

import javax.validation.constraints.NotNull;

public class PasswordRequest {

    private Long userId;
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;

    public PasswordRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
