package com.bartosz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartosz.dao.IncidentDAO;
import com.bartosz.domain.Incident;

@Service
public class IncidentServiceImplementation implements IncidentService{
	
	@Autowired
	IncidentDAO dao;
	
	@Override
	public Incident findIncidentById(int id) {
		if (isIncidentInDatabase(id)) {
			return dao.findById(id).get();
		}
		return null;
	}

	@Override
	public List<Incident> findAllIncidents() {
		return dao.findAll();
	}
	
	@Override
	public Incident addIncident(Incident incident) {
		if (isIncidentInDatabase(incident.getIncidentId())) {
			return null;
		}
		return dao.save(incident);
	}
	
	@Override
	public boolean removeIncident(int id) {
		if (isIncidentInDatabase(id)) {
			dao.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isIncidentInDatabase(int id) {
		return dao.existsById(id);
	}

}
