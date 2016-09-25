package com.weshare.wesharespring.entity.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude={"topicId"})
public class TopicResponse {

    @NonNull
    private Long topicId;
    @NonNull
    private Long userId;
    private String firstName;
    private String lastName;
    private String headShotUrl;
    private String workCompany;
    private String workPosition;
    private Integer phoneRate;
    private Integer meetupRate;
    private String topicTitle;
    private String topicDetail;
    private String topicTarget;
    private String topicType;
    private Integer topicLength;
    private Integer status;
    private Long timeCreated;
    private Long timeUpdated;
}
