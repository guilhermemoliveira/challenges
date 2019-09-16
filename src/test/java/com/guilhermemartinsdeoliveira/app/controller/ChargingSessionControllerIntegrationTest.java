package com.guilhermemartinsdeoliveira.app.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.guilhermemartinsdeoliveira.app.model.composers.ChargingSessionDTOComposer;
import com.guilhermemartinsdeoliveira.app.model.constants.ChargingSessionRoute;
import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionDTO;
import com.guilhermemartinsdeoliveira.app.model.entities.ChargingSession;
import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;
import com.guilhermemartinsdeoliveira.app.model.repository.ChargingSessionRepository;
import com.guilhermemartinsdeoliveira.app.service.ChargingSessionService;
import com.guilhermemartinsdeoliveira.app.utils.GsonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("unused")
public class ChargingSessionControllerIntegrationTest {

	private Gson gson;
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@InjectMocks
	private ChargingSessionService chargingSessionService;

	@MockBean
	private ChargingSessionRepository chargingSessionRepository;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		gson = GsonUtils.getGson();
		MockitoAnnotations.initMocks(this);
		// chargingSessionRepository = Mockito.mock(ChargingSessionRepository.class);
	}

	@Test
	public void shouldFailWhenInsertNewChargingSessionWithRequestBodyNull() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(ChargingSessionRoute.ROUTE_INSERT_CHARGING_SESSION)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void shouldFailWhenInsertNewChargingSessionWithStationIdNull() throws Exception {
		ChargingSessionDTO request = ChargingSessionDTOComposer.getNewRandomChargingSessionWithStationIdNull();
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(ChargingSessionRoute.ROUTE_INSERT_CHARGING_SESSION)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(gson.toJson(request)))
				.andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void shouldSuccessWhenInsertNewChargingSession() throws Exception {
		ChargingSessionDTO request = ChargingSessionDTOComposer.getChargingSessionWithOnlyStationId("ABC-12345");
		ChargingSessionDTO response = ChargingSessionDTOComposer.getNewRandomChargingSessionWithoutStoppedAtDate();

		when(chargingSessionRepository.insert(request)).thenReturn(new ChargingSession(response));

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(ChargingSessionRoute.ROUTE_INSERT_CHARGING_SESSION)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(gson.toJson(request)))
				.andExpect(status().isCreated()).andReturn();
	}

	@Test
	public void shouldFailWhenStopChargingSessionWithInvalidId() throws Exception {
		ChargingSessionDTO request = ChargingSessionDTOComposer.getNewRandomChargingSessionWithIdNull();
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put(ChargingSessionRoute.ROUTE_STOP_CHARGING_SESSION, "INVALID-ID")
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(gson.toJson(request)))
				.andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void shouldFailWhenStopChargingSessionWithUnexistentId() throws Exception {

		ChargingSession mockedSession = new ChargingSession(UUID.randomUUID(), "NEW-CHARGING-SESSION",
				LocalDateTime.now(), StatusEnum.IN_PROGRESS);

		ChargingSessionDTO chargingSessionDTO = new ChargingSessionDTO(mockedSession);

		when(chargingSessionRepository.findById(mockedSession.getId())).thenReturn(Optional.of(mockedSession));
		when(chargingSessionRepository.update(mockedSession)).thenReturn(mockedSession);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put(ChargingSessionRoute.ROUTE_STOP_CHARGING_SESSION, UUID.randomUUID())
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void shouldSuccessWhenStopChargingSession() throws Exception {

		ChargingSession mockedSession = new ChargingSession(UUID.randomUUID(), "NEW-CHARGING-SESSION",
				LocalDateTime.now(), StatusEnum.IN_PROGRESS);

		ChargingSessionDTO chargingSessionDTO = new ChargingSessionDTO(mockedSession);

		when(chargingSessionRepository.findById(mockedSession.getId())).thenReturn(Optional.of(mockedSession));
		when(chargingSessionRepository.update(mockedSession)).thenReturn(mockedSession);

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.put(ChargingSessionRoute.ROUTE_STOP_CHARGING_SESSION, chargingSessionDTO.getId())
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn();

		ChargingSessionDTO resultDTO = gson.fromJson(result.getResponse().getContentAsString(),
				ChargingSessionDTO.class);

		assertNotNull(resultDTO);
		assertNotNull(resultDTO.getStoppedAt());
	}

}
