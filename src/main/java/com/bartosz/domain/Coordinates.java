package com.bartosz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordinates {

	private double lat;
	private double lng;
	
	public String toString() {
		return "" + this.lat + "," + this.lng;
	}
}
