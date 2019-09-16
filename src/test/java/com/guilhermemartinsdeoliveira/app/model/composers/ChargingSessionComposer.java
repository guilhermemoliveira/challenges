package com.guilhermemartinsdeoliveira.app.model.composers;

import java.time.LocalDateTime;
import java.util.UUID;

import com.guilhermemartinsdeoliveira.app.model.entities.ChargingSession;
import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;

public class ChargingSessionComposer {

	private ChargingSessionComposer() {

	}
	
	public static ChargingSession getNewRandomChargingSessionWithoutStoppedAtDate(String stationId) {
		LocalDateTime time = LocalDateTime.now();

		return new ChargingSession(UUID.randomUUID(), stationId, time,
				StatusEnum.IN_PROGRESS);

	}
	
	public static ChargingSession getNewRandomChargingSessionWithStoppedAtDate(String stationId) {
		LocalDateTime time = LocalDateTime.now();

		return new ChargingSession(UUID.randomUUID(), stationId, time,
				StatusEnum.IN_PROGRESS, time.plusDays(20));

	}
}
