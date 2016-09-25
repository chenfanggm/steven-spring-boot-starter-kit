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
@EqualsAndHashCode(exclude={"appointmentId"})
public class Appointment implements Serializable {
	@NonNull
	private Long appointmentId;
	@NonNull
	private Long userId;
	@NonNull
	private Integer appointmentType;
	@NonNull
	private Long topicId;
	private String summary;
	private String question;
	private Long meetupTime;
	private String meetupAddress;
	private String contactWechat;
	private Integer status;
	private Long timeCreated;
	private Long timeUpdated;
}
