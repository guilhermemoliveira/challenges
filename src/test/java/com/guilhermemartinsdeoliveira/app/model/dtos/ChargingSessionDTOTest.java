package com.guilhermemartinsdeoliveira.app.model.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guilhermemartinsdeoliveira.app.model.adapters.LocalDateTimeAdapter;
import com.guilhermemartinsdeoliveira.app.model.composers.ChargingSessionDTOComposer;

@SpringBootTest
public class ChargingSessionDTOTest {

	private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
	.create();

	@Test
	public void testChargingSessionDTOSerialization() {

		ChargingSessionDTO chargingSessionDTO = ChargingSessionDTOComposer
				.getChargingSessionWithFixedStartedAtDateAndStoppedAtDate();
		String serializedChargingSessionDTO = gson.toJson(chargingSessionDTO);
		String stringChargingSessionDTO = ChargingSessionDTOComposer
				.getStringJsonChargingSessionDTOWithFixedStartedAtDateAndNoStoppedAtDate();

		assertEquals(serializedChargingSessionDTO, stringChargingSessionDTO);
	}

	@Test
	public void testChargingSessionDTODeserialization() {

		String stringChargingSessionDTO = ChargingSessionDTOComposer
				.getStringJsonChargingSessionDTOWithFixedStartedAtDateAndNoStoppedAtDate();
		ChargingSessionDTO dto = gson.fromJson(stringChargingSessionDTO, ChargingSessionDTO.class);

		assertNotNull(dto);
		assertEquals(dto.getClass(), ChargingSessionDTO.class);
	}
	
}
