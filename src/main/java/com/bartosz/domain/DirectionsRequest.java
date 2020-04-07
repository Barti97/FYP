package com.bartosz.domain;

import com.google.maps.model.LatLng;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DirectionsRequest {

//	private Coordinates start;
//	private Coordinates end;

	private LatLng start;
	private LatLng end;
	private boolean avoidIncidents;
	private String preference;
	
}
