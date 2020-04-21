package com.bartosz.service;

import java.util.List;

import com.bartosz.domain.Incident;

public interface IncidentService {

	Incident findIncidentById(int id);
	List<Incident> findAllIncidents();
	Incident addIncident(Incident incident);
	boolean removeIncident(int id);
	boolean isIncidentInDatabase(int id);
	boolean updateIncident(int id, int input);
	
}
