package com.weshare.wesharespring.entity.Response;

import lombok.*;
import org.skife.jdbi.v2.sqlobject.Bind;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude={"emailId"})
public class EmailResponse {

    @NotNull
    private Long emailId;
    @NotNull
    private Long userId;
    private Integer emailType;
    private String emailSubject;
    private String emailContent;
    private Integer status;
    private Long timeCreated;
}
