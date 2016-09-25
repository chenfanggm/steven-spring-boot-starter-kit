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
@EqualsAndHashCode(exclude={"userId", "emailType", "emailId"})
public class Mailer implements Serializable {
    @NonNull
    private Long userId;
    @NonNull
    private Integer emailType;
    @NonNull
    private Long emailId;
}
