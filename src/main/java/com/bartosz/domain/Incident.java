package com.bartosz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int incidentId;

	@Column(nullable = false)
	private String typeOfIncident;

	@Column(nullable = false)
	private String incidentCoordinates;

	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;

	public Incident(String typeOfIncident, String incidentCoordinates, User owner) {
		this.typeOfIncident = typeOfIncident;
		this.incidentCoordinates = incidentCoordinates;
		this.owner = owner;
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
