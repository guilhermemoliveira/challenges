package com.guilhermemartinsdeoliveira.app.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionDTO;
import com.guilhermemartinsdeoliveira.app.model.entities.ChargingSession;
import com.guilhermemartinsdeoliveira.app.model.repository.ChargingSessionRepository;

@Service
public class ChargingSessionService {

	@Autowired
	private ChargingSessionRepository chargingSessionRepository;

	public ChargingSessionDTO insert(ChargingSessionDTO chargingSessionDTO) {
		ChargingSession chargingSession = chargingSessionRepository.insert(chargingSessionDTO);
		return new ChargingSessionDTO(chargingSession);
	}

	public Optional<ChargingSessionDTO> stopChargingSessionById(UUID id) {
		Optional<ChargingSession> chargingSessionOptional = chargingSessionRepository.findById(id);

		if (chargingSessionOptional.isPresent()) {
			ChargingSession chargingSession = chargingSessionOptional.get();
			chargingSession.setStoppedAt(LocalDateTime.now());
			return Optional.of(new ChargingSessionDTO(chargingSessionRepository.update(chargingSession)));
		} else {
			return Optional.empty();
		}
	}

}
