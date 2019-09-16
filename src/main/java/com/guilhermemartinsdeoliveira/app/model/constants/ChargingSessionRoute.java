package com.guilhermemartinsdeoliveira.app.model.constants;

public class ChargingSessionRoute {

	private ChargingSessionRoute() {

	}

	public static final String ROUTE_INSERT_CHARGING_SESSION = "/chargingSessions";
	public static final String ROUTE_STOP_CHARGING_SESSION = "/chargingSessions/{id}";
	public static final String ROUTE_GET_ALL_CHARGING_SESSION = "/chargingSessions";
	public static final String ROUTE_GET_CHARGING_SESSION_SUMMARY = "/chargingSessions/summary";

}
