package com.guilhermemartinsdeoliveira.app.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionDTO;
import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionSummaryDTO;
import com.guilhermemartinsdeoliveira.app.model.entities.ChargingSession;
import com.guilhermemartinsdeoliveira.app.model.enums.StatusEnum;
import com.guilhermemartinsdeoliveira.app.repositories.ChargingSessionRepository;

@Service
public class ChargingSessionService {

	@Autowired
	private ChargingSessionRepository chargingSessionRepository;

	/**
	 * Inserts a new ChargingSession entity in in-memory map by ChargingSessionDTO's stationId.
	 * 
	 * @param chargingSessionDTO containing stationId.
	 * @return The just created ChargingSession in a ChargingSessionDTO.
	 */
	public ChargingSessionDTO insert(ChargingSessionDTO chargingSessionDTO) {
		ChargingSession chargingSession = chargingSessionRepository.insert(chargingSessionDTO.getStationId());
		return new ChargingSessionDTO(chargingSession);
	}

	/**
	 * Stops an existent ChargingSession entity by id, 
	 * filling stoppedAt and setting statusEnum to FINISHED, if entity exists in in-memory map.
	 * If entity does not exist, returns empty Optional.
	 * 
	 * @param id
	 * @return Optional containing updated ChargingSession in a ChargingSessionDTO or empty Optional.
	 */
	public Optional<ChargingSessionDTO> stopChargingSessionById(UUID id) {
		Optional<ChargingSession> chargingSessionOptional = chargingSessionRepository.findById(id);

		if (chargingSessionOptional.isPresent()) {
			ChargingSession chargingSession = chargingSessionOptional.get();
			chargingSession.setStoppedAt(LocalDateTime.now());
			chargingSession.setStatus(StatusEnum.FINISHED);
			return Optional.of(new ChargingSessionDTO(chargingSessionRepository.update(chargingSession)));
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Gets all existing ChargingSession entities in in-memory map, converted to ChargingSessionDTOs.
	 * 
	 * @return List containing ChargingSessionDTOs
	 */
	public List<ChargingSessionDTO> getAllChargingSessions() {
		return chargingSessionRepository.getAllChargingSessions().stream()
				.map(chargingSession -> new ChargingSessionDTO(chargingSession)).collect(Collectors.toList());
	}
	
	/**
	 * Gets summary (ChargingSessionSummaryDTO) of current in-memory map.
	 * 
	 * totalCount represents all existing ChargingSessions.
	 * startedCount represents all ChargingSessions started in the last minute, specifically.
	 * stoppedCount represents all ChargingSessions stopped in the last minute, specifically.
	 * 
	 * @return A single summary object containing the attributes mentioned above.
	 */
	public ChargingSessionSummaryDTO getChargingSessionsSummary() {
		Integer totalCount = 0;
		Integer startedCountInLastMinute = 0;
		Integer stoppedCountInLastMinute = 0;
		LocalDateTime now = LocalDateTime.now();
		List<ChargingSession> chargingSessions = chargingSessionRepository.getAllChargingSessions();

		for (ChargingSession chargingSession : chargingSessions) {
			if (Duration.between(chargingSession.getStartedAt(), now).getSeconds() <= 60) {
				startedCountInLastMinute++;
			}

			if (chargingSession.getStoppedAt() != null
					&& Duration.between(chargingSession.getStoppedAt(), now).getSeconds() <= 60) {
				stoppedCountInLastMinute++;
			}

			totalCount++;
		}

		return new ChargingSessionSummaryDTO(totalCount, startedCountInLastMinute, stoppedCountInLastMinute);
	}

}
