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
@EqualsAndHashCode(exclude={"topicId"})
public class Topic implements Serializable {

    @NonNull
    private Long topicId;
    @NonNull
    private Long userId;
    private String topicTitle;
    private String topicDetail;
    private String topicTarget;
    private String topicType;
    private Integer topicLength;
    private Integer status;
    private Long timeCreated;
    private Long timeUpdated;

}