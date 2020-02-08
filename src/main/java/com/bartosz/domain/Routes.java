package com.bartosz.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Routes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int routeId;

	@Column(nullable = false)
	private String routeCoordinates;

	public Routes(String routeCoordinates) {
		this.routeCoordinates = routeCoordinates;
	}

	public String toString() {
		String output = "Route: ";
		if (this.routeId != 0) {
			output += this.routeId;
		}
		output += "\n\tCoordinates: " + this.routeCoordinates;
		return output;
	}
}
