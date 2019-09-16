package com.guilhermemartinsdeoliveira.app.model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;

@SpringBootTest
public class ChargingSessionTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChargingSession.class);

	@Test(expected = NullPointerException.class)
	public void shouldFailWhenCreateChargingSessionWithIdNull() {
		ChargingSession chargingSession = new ChargingSession(null, "stationId", LocalDateTime.now(),
				StatusEnum.IN_PROGRESS);
		LOGGER.info(chargingSession.toString());
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailWhenCreateChargingSessionWithStationIdNull() {
		ChargingSession chargingSession = new ChargingSession(UUID.randomUUID(), null, LocalDateTime.now(),
				StatusEnum.IN_PROGRESS);
		LOGGER.info(chargingSession.toString());
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailWhenCreateChargingSessionWithStartedAtNull() {
		ChargingSession chargingSession = new ChargingSession(UUID.randomUUID(), "StationId", null,
				StatusEnum.IN_PROGRESS);
		LOGGER.info(chargingSession.toString());
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailWhenCreateChargingSessionWithStatusEnumNull() {
		ChargingSession chargingSession = new ChargingSession(UUID.randomUUID(), "StationId", LocalDateTime.now(),
				null);
		LOGGER.info(chargingSession.toString());
	}
}
