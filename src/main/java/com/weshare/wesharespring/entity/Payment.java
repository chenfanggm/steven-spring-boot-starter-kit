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
@EqualsAndHashCode(exclude={"paymentId"})
public class Payment implements Serializable {
	@NonNull
	private Long paymentId;
	@NonNull
	private Long userId;
	private String paymentType;
	private String paymentAccount;
	private Integer status;
	private Long timeCreated;
	private Long timeUpdated;
}
