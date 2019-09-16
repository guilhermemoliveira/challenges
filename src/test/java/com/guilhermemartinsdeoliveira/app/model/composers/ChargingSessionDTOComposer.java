package com.guilhermemartinsdeoliveira.app.model.composers;

import java.time.LocalDateTime;
import java.util.UUID;

import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionDTO;
import com.guilhermemartinsdeoliveira.app.model.entities.ChargingSession;
import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;

public class ChargingSessionDTOComposer {

	private ChargingSessionDTOComposer() {

	}

	public static ChargingSessionDTO getChargingSessionWithFixedStartedAtDateAndStoppedAtDate() {
		LocalDateTime time = LocalDateTime.now().withYear(2019).withMonth(9).withDayOfMonth(15).withHour(14)
				.withMinute(50).withSecond(14).withNano(688);

		ChargingSession chargingSession = new ChargingSession(UUID.fromString("08253dce-bf92-41dc-b592-1dd91d41bd5c"),
				"ABC-12345", time, StatusEnum.IN_PROGRESS, time.plusDays(10));

		return new ChargingSessionDTO(chargingSession);
	}

	public static String getStringJsonChargingSessionDTOWithFixedStartedAtDateAndNoStoppedAtDate() {
		LocalDateTime time = LocalDateTime.now().withYear(2019).withMonth(9).withDayOfMonth(15).withHour(14)
				.withMinute(50).withNano(688);

		return "{" + "\"id\":\"08253dce-bf92-41dc-b592-1dd91d41bd5c\"," + "\"stationId\":\"ABC-12345\","
				+ "\"startedAt\":\"" + time.toString() + "\"," + "\"stoppedAt\":\"" + time.plusDays(10).toString()
				+ "\"," + "\"status\":\"IN_PROGRESS\"" + "}";

	}

	public static ChargingSessionDTO getChargingSessionWithoutDTOStoppedAtDate() {
		LocalDateTime time = LocalDateTime.now().withYear(2019).withMonth(9).withDayOfMonth(15).withHour(14)
				.withMinute(50).withSecond(14).withNano(688);

		ChargingSession chargingSession = new ChargingSession(UUID.fromString("08253dce-bf92-41dc-b592-1dd91d41bd5c"),
				"ABC-12345", time, StatusEnum.IN_PROGRESS);

		return new ChargingSessionDTO(chargingSession);
	}

	public static ChargingSessionDTO getNewChargingSessionWithStationIdAndStatusEnum(String stationId,
			StatusEnum statusEnum) {
		LocalDateTime time = LocalDateTime.now();

		ChargingSession chargingSession = new ChargingSession(UUID.randomUUID(), stationId, time, statusEnum);

		return new ChargingSessionDTO(chargingSession);
	}

	public static ChargingSessionDTO getChargingSessionWithOnlyStationId(String stationId) {
		ChargingSessionDTO chargingSessionDTO = new ChargingSessionDTO();
		chargingSessionDTO.setStationId(stationId);
		return chargingSessionDTO;
	}

	public static ChargingSessionDTO getNewRandomChargingSessionWithStationIdNull() {
		LocalDateTime time = LocalDateTime.now();

		ChargingSession chargingSession = new ChargingSession(UUID.randomUUID(), "stationId", time,
				StatusEnum.IN_PROGRESS);
		chargingSession.setStationId(null);

		return new ChargingSessionDTO(chargingSession);
	}
	
	public static ChargingSessionDTO getNewRandomChargingSessionWithIdNull() {
		LocalDateTime time = LocalDateTime.now();

		ChargingSession chargingSession = new ChargingSession(UUID.randomUUID(), "stationId", time,
				StatusEnum.IN_PROGRESS);
		chargingSession.setId(null);

		return new ChargingSessionDTO(chargingSession);
	}
	
	public static ChargingSessionDTO getNewRandomChargingSessionWithoutStoppedAtDate() {
		LocalDateTime time = LocalDateTime.now();

		ChargingSession chargingSession = new ChargingSession(UUID.randomUUID(), "stationId", time,
				StatusEnum.IN_PROGRESS);

		return new ChargingSessionDTO(chargingSession);
	}
}
