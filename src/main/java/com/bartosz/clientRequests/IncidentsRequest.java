package com.bartosz.clientRequests;

import com.google.maps.model.LatLng;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncidentsRequest {
	
	private String type;
	private LatLng location;
	private String userEmail;

}
