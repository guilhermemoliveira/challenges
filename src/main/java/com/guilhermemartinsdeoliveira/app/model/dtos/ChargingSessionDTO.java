package com.guilhermemartinsdeoliveira.app.model.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.guilhermemartinsdeoliveira.app.model.entities.ChargingSession;
import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(exclude = { "startedAt", "stoppedAt", "status" })
public class ChargingSessionDTO implements Serializable {

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	private UUID id;
	
	@NotNull
	private String stationId;
	
	private LocalDateTime startedAt;
	private LocalDateTime stoppedAt;
	private StatusEnum status;
	
	public ChargingSessionDTO() {
		
	}

	public ChargingSessionDTO(ChargingSession chargingSession) {
		this.id = chargingSession.getId();
		this.stationId = chargingSession.getStationId();
		this.startedAt = chargingSession.getStartedAt();
		this.status = chargingSession.getStatus();
		this.stoppedAt = chargingSession.getStoppedAt();
	}
}
