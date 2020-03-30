package com.bartosz.domain;

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
	private Coordinates location;
	private String userEmail;

}
