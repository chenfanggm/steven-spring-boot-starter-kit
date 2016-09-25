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
@EqualsAndHashCode(exclude={"emailId"})
public class Email implements Serializable {
    @NonNull
    private Long emailId;
    private String emailSubject;
    private String emailContent;
    private Integer status;
    private Long timeCreated;
}
