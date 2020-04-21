package com.bartosz.controllers;

import com.bartosz.domain.Incident;
import com.google.maps.model.LatLng;

public interface ServerController {
	
	public double calculateDistance(LatLng p1, LatLng p2);
	public String directions(LatLng start, LatLng end, boolean avoidIncidents, String preference);
	public Incident checkClosestIncident(LatLng location);
	
}
