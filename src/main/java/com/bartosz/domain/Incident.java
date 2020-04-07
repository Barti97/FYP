package com.bartosz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.maps.model.LatLng;

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
	private double lat;
	
	@Column(nullable = false)
	private double lng;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;

	public Incident(String typeOfIncident, LatLng incidentCoordinates, User owner) {
		this.typeOfIncident = typeOfIncident;
		this.owner = owner;

		if(incidentCoordinates != null) {
			this.lat = incidentCoordinates.lat;
			this.lng = incidentCoordinates.lng;
		} else {
			this.lat = 0.0;
			this.lng = 0.0;
		}
	}

	public String toString() {
		String output = "Incident: ";
		if (this.incidentId != 0) {
			output += this.incidentId;
		}
		output += "\n\tType: " + this.typeOfIncident + "\n\tCoordinates: " + this.lat + "," + this.lng ;
		return output;
	}

}
