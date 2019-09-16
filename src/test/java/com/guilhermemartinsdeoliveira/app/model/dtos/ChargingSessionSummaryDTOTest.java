package com.guilhermemartinsdeoliveira.app.model.dtos;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guilhermemartinsdeoliveira.app.config.adapters.LocalDateTimeAdapter;
import com.guilhermemartinsdeoliveira.app.model.composers.ChargingSessionSummaryComposer;

@SpringBootTest
public class ChargingSessionSummaryDTOTest {

	private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
			.create();

	@Test
	public void testChargingSessionSummaryDTOSerialization() {

		ChargingSessionSummaryDTO dto = ChargingSessionSummaryComposer.getNewRandomChargingSessionSummary();
		String serializedChargingSessionSummaryDTO = gson.toJson(dto);

		assertEquals(dto, gson.fromJson(serializedChargingSessionSummaryDTO, ChargingSessionSummaryDTO.class));

	}

	@Test
	public void testChargingSessionSummaryDTODeserialization() {

		String stringDTO = ChargingSessionSummaryComposer.getStringNewChargingSessionSummary(1, 2, 3);
		ChargingSessionSummaryDTO dto = gson.fromJson(stringDTO, ChargingSessionSummaryDTO.class);
		ChargingSessionSummaryDTO otherDTO = new ChargingSessionSummaryDTO(1, 2, 3);

		assertEquals(otherDTO, dto);
	}
	
}
