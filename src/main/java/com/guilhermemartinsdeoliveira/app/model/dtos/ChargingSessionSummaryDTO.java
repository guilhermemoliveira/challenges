package com.guilhermemartinsdeoliveira.app.model.dtos;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ChargingSessionSummaryDTO implements Serializable {
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1L;
	
	private Integer totalCount;
	private Integer startedCount;
	private Integer stoppedCount;
	
	public ChargingSessionSummaryDTO() {
		
	}

}
