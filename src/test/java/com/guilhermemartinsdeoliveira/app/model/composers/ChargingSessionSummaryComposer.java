package com.guilhermemartinsdeoliveira.app.model.composers;

import java.util.Random;

import com.guilhermemartinsdeoliveira.app.model.dtos.ChargingSessionSummaryDTO;

public class ChargingSessionSummaryComposer {

	private ChargingSessionSummaryComposer() {

	}

	public static ChargingSessionSummaryDTO getNewRandomChargingSessionSummary() {
		Random random = new Random();
		return new ChargingSessionSummaryDTO(random.nextInt(6) + 5, random.nextInt(1) + 5, random.nextInt(1) + 5);
	}

	public static String getStringNewChargingSessionSummary(Integer totalCount, Integer startedCount,
			Integer stoppedCount) {
		return "{\"totalCount\":" + totalCount + ",\"startedCount\":" + startedCount + ",\"stoppedCount\":"
				+ stoppedCount + "}";
	}
}
