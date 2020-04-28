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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int routeId;
	
	@Column(nullable = false, unique = true)
	private String title;

	@Column(nullable = false)
	private double start_lat;
	
	@Column(nullable = false)
	private double start_lng;

	@Column(nullable = false)
	private double end_lat;

	@Column(nullable = false)
	private double end_lng;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	
	public Route(String title, LatLng start, LatLng end, User owner) {
		this.title = title;
		if(start != null) {
			this.start_lat = start.lat;
			this.start_lng = start.lng;
		} else {
			this.start_lat = 0.0;
			this.start_lng = 0.0;
		}

		if(end != null) {
			this.end_lat = end.lat;
			this.end_lng = end.lng;
		} else {
			this.end_lat = 0.0;
			this.end_lng = 0.0;
		}
		this.owner = owner;
	}
	
	public String toString() {
		return this.title + "\n[" + this.start_lat + "," + this.start_lng + "]"
				+ "\n[" + this.end_lat + "," + this.end_lng + "]";
	}
	
	public void setStart(LatLng start) {
		if(start != null) {
			this.start_lat = start.lat;
			this.start_lng = start.lng;
		} else {
			this.start_lat = 0.0;
			this.start_lng = 0.0;
		}
	}
	
	public void setEnd(LatLng end) {
		if(end != null) {
			this.end_lat = end.lat;
			this.end_lng = end.lng;
		} else {
			this.end_lat = 0.0;
			this.end_lng = 0.0;
		}
	}

}
