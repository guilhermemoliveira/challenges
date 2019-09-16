package com.guilhermemartinsdeoliveira.app.model.dtos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.guilhermemartinsdeoliveira.app.model.composers.ChargingSessionDTOComposer;
import com.guilhermemartinsdeoliveira.app.utils.GsonUtils;

@SpringBootTest
public class ChargingSessionDTOTest {

	@Test
	public void testChargingSessionDTOSerialization() {

		ChargingSessionDTO chargingSessionDTO = ChargingSessionDTOComposer.getChargingSessionWithFixedStartedAtDateAndStoppedAtDate();
		String serializedChargingSessionDTO = GsonUtils.getGson().toJson(chargingSessionDTO);
		String stringChargingSessionDTO = ChargingSessionDTOComposer.getStringJsonChargingSessionDTOWithFixedStartedAtDateAndNoStoppedAtDate();

		assertEquals(serializedChargingSessionDTO, stringChargingSessionDTO);
	}

	@Test
	public void testChargingSessionDTODeserialization() {

		String stringChargingSessionDTO = ChargingSessionDTOComposer.getStringJsonChargingSessionDTOWithFixedStartedAtDateAndNoStoppedAtDate();
		ChargingSessionDTO dto = GsonUtils.getGson().fromJson(stringChargingSessionDTO, ChargingSessionDTO.class);

		assertNotNull(dto);
		assertEquals(dto.getClass(), ChargingSessionDTO.class);
	}
}
