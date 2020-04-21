package com.bartosz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@Column(nullable = false)
	private int reportNumber;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;

	public Incident(String typeOfIncident, LatLng incidentCoordinates, int reportNumber,  User owner) {
		this.typeOfIncident = typeOfIncident;
		this.reportNumber = reportNumber;
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
	
	public String drawPolygon() {
		
		double metre = 0.0000146;
		
		LatLng p1 = new LatLng(this.lat + (5 * metre), this.lng - (5 * metre)); // Top left
		LatLng p2 = new LatLng(this.lat + (5 * metre), this.lng + (5 * metre)); // Top right
		LatLng p3 = new LatLng(this.lat - (5 * metre), this.lng + (5 * metre)); // Bottom right
		LatLng p4 = new LatLng(this.lat - (5 * metre), this.lng - (5 * metre)); // Bottom left
		
		String p1s = latLngAsArray(p1);
		String p2s = latLngAsArray(p2);
		String p3s = latLngAsArray(p3);
		String p4s = latLngAsArray(p4);
		
		String polygon = "[" + p1s + ","  + p2s + "," + p3s + "," + p4s + "," + p1s + "]";
//		String polygon = p1+p2+p3+p4+p1;
		return polygon;
	}
	
	private String latLngAsArray(LatLng coord) {
		return "[" + coord.lng + "," + coord.lat + "]";
	}
	

}
