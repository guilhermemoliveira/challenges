package com.guilhermemartinsdeoliveira.app.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionDTO;
import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(exclude = { "startedAt", "stoppedAt", "status" })
public class ChargingSession implements Serializable {

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	private UUID id;
	private String stationId;
	private LocalDateTime startedAt;
	private LocalDateTime stoppedAt;
	private StatusEnum status;

	public ChargingSession(UUID id, String stationId, LocalDateTime startedAt, StatusEnum status) {
		this.id = Objects.requireNonNull(id);
		this.stationId = Objects.requireNonNull(stationId);
		this.startedAt = Objects.requireNonNull(startedAt);
		this.status = Objects.requireNonNull(status);
	}

	public ChargingSession(UUID id, String stationId, LocalDateTime startedAt, StatusEnum status,
			LocalDateTime stoppedAt) {
		this.id = Objects.requireNonNull(id);
		this.stationId = Objects.requireNonNull(stationId);
		this.startedAt = Objects.requireNonNull(startedAt);
		this.status = Objects.requireNonNull(status);
		this.stoppedAt = stoppedAt;
	}

	public ChargingSession(ChargingSessionDTO chargingSessionDTO) {
		this.id = chargingSessionDTO.getId();
		this.stationId = chargingSessionDTO.getStationId();
		this.startedAt = chargingSessionDTO.getStartedAt();
		this.status = chargingSessionDTO.getStatus();
		this.stoppedAt = chargingSessionDTO.getStoppedAt();
	}
}
