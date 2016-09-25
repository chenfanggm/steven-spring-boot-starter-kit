package com.weshare.wesharespring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude={"userId"})
public class User implements Serializable {

    @NonNull
    private Long userId;
    private String email;
    private String username;
    private String password;
    private Integer verified;
    private Integer accessLevel;
    private Integer status;
    private Long lastLogin;
    private Long timeCreated;
    private Long timeUpdated;
}