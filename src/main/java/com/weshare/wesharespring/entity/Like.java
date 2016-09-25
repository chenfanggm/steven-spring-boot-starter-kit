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
@EqualsAndHashCode(exclude={"userAId", "userBId"})
public class Like implements Serializable {
	@NonNull
	private Long userAId;
	@NonNull
	private Long userBId;
	private Integer likeType;
	private Integer status;
	private Long timeCreated;
	private Long timeUpdated;
}
