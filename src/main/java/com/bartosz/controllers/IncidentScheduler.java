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
	IncidentService jobService;

	@Scheduled(fixedRate = 10000)
	public void checkForExpiredJobs() {
		List<Incident> allIncidents = jobService.findAllIncidents();
		for (Incident i : allIncidents) {
//			if (i.getDate().isBefore(LocalDateTime.now().minusDays(20))) {
//				jobService.updateJobActive(j.getJobId(), false);
//			}
		}
	}

}
