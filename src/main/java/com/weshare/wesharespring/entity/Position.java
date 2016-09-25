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
@EqualsAndHashCode(exclude={"positionId"})
public class Position implements Serializable {

	@NonNull
	private Long positionId;
	@NonNull
	private Long userId;
	private String positionCompany;
	private String positionTitle;
	private String positionDetail;
    private Long positionDeadline;
	private Integer status;
	private Long timeCreated;
	private Long timeUpdated;
}
