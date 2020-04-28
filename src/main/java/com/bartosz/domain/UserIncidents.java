package com.bartosz.domain;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


public class UserIncidents {

	@OneToOne
	@JoinColumn
	private User userId;

	@OneToMany
	@JoinColumn
	private List<Incident> incidentId;

	public String toString() {
		String output = "User: " + userId + "\n\tIncident: " + this.incidentId;
		return output;
	}

}
