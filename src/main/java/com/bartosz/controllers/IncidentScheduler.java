package com.bartosz.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bartosz.domain.Incident;
import com.bartosz.service.IncidentService;

@Component
public class IncidentScheduler {

	@Autowired
	IncidentService incidentService;

	@Scheduled(fixedRate = 30000)
	public void checkForExpiredIncidents() {
		List<Incident> allIncidents = incidentService.findAllIncidents();
		for (Incident i : allIncidents) {
			if (i.getTimeAdded().isBefore(LocalDateTime.now().minusMinutes(10)) && i.getReportNumber() < 5) {
				incidentService.removeIncident(i.getIncidentId());
			} else if (i.getTimeAdded().isBefore(LocalDateTime.now().minusMinutes(30)) && i.getReportNumber() < 10) {
				incidentService.removeIncident(i.getIncidentId());
			} else if (i.getTimeAdded().isBefore(LocalDateTime.now().minusHours(1)) && i.getReportNumber() < 20) {
				incidentService.removeIncident(i.getIncidentId());
			} else if (i.getTimeAdded().isBefore(LocalDateTime.now().minusHours(3)) && i.getReportNumber() < 50) {
				incidentService.removeIncident(i.getIncidentId());
			} else if (i.getTimeAdded().isBefore(LocalDateTime.now().minusHours(8)) && i.getReportNumber() < 100) {
				incidentService.removeIncident(i.getIncidentId());
			}
		}
	}

}
