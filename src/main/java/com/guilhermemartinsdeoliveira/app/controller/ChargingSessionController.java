package com.guilhermemartinsdeoliveira.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermemartinsdeoliveira.app.model.constants.ChargingSessionRoute;
import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionDTO;
import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionSummaryDTO;
import com.guilhermemartinsdeoliveira.app.services.ChargingSessionService;

@RestController
public class ChargingSessionController {

	@Autowired
	private ChargingSessionService chargingSessionService;

	@PostMapping(ChargingSessionRoute.ROUTE_INSERT_CHARGING_SESSION)
	public ResponseEntity<ChargingSessionDTO> insertChargingSession(
			@Valid @NotNull @RequestBody ChargingSessionDTO chargingSessionDTO) {
		return new ResponseEntity<>(chargingSessionService.insert(chargingSessionDTO), HttpStatus.CREATED);
	}

	@PutMapping(ChargingSessionRoute.ROUTE_STOP_CHARGING_SESSION)
	public ResponseEntity<ChargingSessionDTO> stopChargingSession(@PathVariable @Valid @NotNull UUID id) {

		Optional<ChargingSessionDTO> response = chargingSessionService.stopChargingSessionById(id);
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@GetMapping(ChargingSessionRoute.ROUTE_GET_ALL_CHARGING_SESSION)
	public ResponseEntity<List<ChargingSessionDTO>> getAllChargingSessions() {
		return new ResponseEntity<>(chargingSessionService.getAllChargingSessions(), HttpStatus.OK);
	}

	@GetMapping(ChargingSessionRoute.ROUTE_GET_CHARGING_SESSION_SUMMARY)
	public ResponseEntity<ChargingSessionSummaryDTO> getChargingSessionSummaryDTO() {
		return new ResponseEntity<>(chargingSessionService.getChargingSessionsSummary(), HttpStatus.OK);
	}

}
