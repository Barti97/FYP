package com.bartosz.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Incidents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int incidentId;

	@Column(nullable = false)
	private String typeOfIncident;

	@Column(nullable = false)
	private String incidentCoordinates;

	public Incidents(String typeOfIncident, String incidentCoordinates) {
		this.incidentCoordinates = incidentCoordinates;
		this.typeOfIncident = typeOfIncident;
	}

	public String toString() {
		String output = "Incident: ";
		if (this.incidentId != 0) {
			output += this.incidentId;
		}
		output += "\n\tType: " + this.typeOfIncident + "\n\tCoordinates: " + this.incidentCoordinates;
		return output;
	}

}
