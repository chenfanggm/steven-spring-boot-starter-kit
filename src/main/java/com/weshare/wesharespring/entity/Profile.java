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
public class Profile implements Serializable {
    @NonNull
    private Long userId;
    private String firstName;
    private String lastName;
    private Integer gender;
    private String major;
    private String summary;
    private String contactWechat;
    private String contactEmail;
    private String contactPhone;
    private String workCity;
    private String workState;
    private String workAddress;
    private String workPosition;
    private String workCompany;
    private Integer workYear;
    private Integer workReferStatus;
    private String workReferPosition;
    private Integer phoneRate;
    private Integer meetupRate;
    private String meetupBonus;
    private String availableTime;
    private String preferTime;
    private String preferAddress;
    private String preferPayment;
    private String headShotUrl;
    private Integer timeZone;
    private Integer status;
    private Long timeCreated;
    private Long timeUpdated;
}
