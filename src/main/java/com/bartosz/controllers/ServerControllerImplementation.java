package com.bartosz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartosz.domain.Incident;
import com.bartosz.requests.ORSRequests;
import com.bartosz.service.IncidentService;
import com.google.maps.model.LatLng;

@Service
public class ServerControllerImplementation implements ServerController {
	
	@Autowired
	IncidentService incidentService;
	
	public double calculateDistance(LatLng p1, LatLng p2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(p2.lat - p1.lat);
	    double lngDistance = Math.toRadians(p2.lng - p1.lng);
	    
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(p1.lat)) * Math.cos(Math.toRadians(p2.lat))
	            * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
	    
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance);
	}
	
	public String directions(LatLng start, LatLng end, boolean avoidIncidents, String preference) {
		List<Incident> incidents = incidentService.findAllIncidents();
		String res = ORSRequests.getDirections(start, end, avoidIncidents, preference, incidents);
		if (res == null) {
			return "{\"error\":\"PARSE_ERROR\"}";
		}
	
		return "{\"result\":" + res + "}";
	}
	
	public Incident checkClosestIncident(LatLng location) {
		List<Incident> incidents = incidentService.findAllIncidents();
//		for XD;
		double distance = Double.MAX_VALUE;
		Incident id = null;
		for (Incident inc : incidents) {
			double newDistance = calculateDistance(location, new LatLng(inc.getLat(), inc.getLng()));
			if (newDistance < distance ) {
				distance = newDistance;
				id = inc;
			}
		}
		if (distance < 500) {
			return id;
		}
		else {
			return null;
		}
		
	}

}
