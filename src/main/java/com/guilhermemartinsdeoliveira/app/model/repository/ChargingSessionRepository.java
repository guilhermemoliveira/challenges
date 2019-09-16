package com.guilhermemartinsdeoliveira.app.model.repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionDTO;
import com.guilhermemartinsdeoliveira.app.model.entities.ChargingSession;
import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;

@Repository
public class ChargingSessionRepository {

	private static Map<UUID, ChargingSession> chargingSessions;

	@Autowired
	public ChargingSessionRepository() {
		chargingSessions = Collections.synchronizedMap(new HashMap<>());
	}

	public ChargingSession insert(ChargingSessionDTO chargingSessionDTO) {
			ChargingSession chargingSession = this.createNewInProgressChargingSession(chargingSessionDTO);
			chargingSessions.put(chargingSession.getId(), chargingSession);

		return chargingSession;
	}

	private ChargingSession createNewInProgressChargingSession(ChargingSessionDTO chargingSessionDTO) {
		return new ChargingSession(UUID.randomUUID(), chargingSessionDTO.getStationId(), LocalDateTime.now(),
				StatusEnum.IN_PROGRESS);
	}

	public Optional<ChargingSession> findById(UUID id) {
		return Optional.ofNullable(chargingSessions.get(id));
	}

	public ChargingSession update(ChargingSession chargingSession) {
		chargingSessions.replace(chargingSession.getId(), chargingSession);
		return chargingSession;
	}
}
