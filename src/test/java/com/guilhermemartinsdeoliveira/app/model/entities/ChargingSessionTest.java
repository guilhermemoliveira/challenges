package com.guilhermemartinsdeoliveira.app.model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;

@SpringBootTest
public class ChargingSessionTest {

	@Test(expected = NullPointerException.class)
	public void shouldFailWhenCreateChargingSessionWithIdNull() {
		new ChargingSession(null, "stationId", LocalDateTime.now(),
				StatusEnum.IN_PROGRESS);
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailWhenCreateChargingSessionWithStationIdNull() {
		new ChargingSession(UUID.randomUUID(), null, LocalDateTime.now(),
				StatusEnum.IN_PROGRESS);
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailWhenCreateChargingSessionWithStartedAtNull() {
		new ChargingSession(UUID.randomUUID(), "StationId", null,
				StatusEnum.IN_PROGRESS);
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailWhenCreateChargingSessionWithStatusEnumNull() {
		new ChargingSession(UUID.randomUUID(), "StationId", LocalDateTime.now(),
				null);
	}
}
