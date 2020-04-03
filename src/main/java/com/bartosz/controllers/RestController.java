package com.bartosz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bartosz.domain.AddressRequest;
import com.bartosz.domain.DirectionsRequest;
import com.bartosz.domain.Incident;
import com.bartosz.domain.IncidentsRequest;
import com.bartosz.domain.PlaceResponse;
import com.bartosz.domain.User;
import com.bartosz.requests.ORSRequests;
import com.bartosz.service.IncidentService;
import com.bartosz.service.UserService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@Autowired
	IncidentService incidentService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(path = "/directions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String directions(@RequestBody DirectionsRequest request) {
		String res = ORSRequests.getDirections(request.getStart(), request.getEnd());
		if (res == null) {
			return "{\"error\":\"PARSE_ERROR\"}";
		}
		
		return "{\"result\":" + res + "}";
	}
	
	@PostMapping(path = "/incidents", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String incidents(@RequestBody IncidentsRequest request) {
		System.out.println(request.getType());
		System.out.println(request.getLocation());
		System.out.println(request.getUserEmail());
		User sender = userService.findByEmail(request.getUserEmail());
		if (sender == null) {
			return "{\"result\":\"FAILED TO FIND USER\"}";
		}
		
		Incident i = incidentService.addIncident(new Incident(request.getType(), request.getLocation(), sender));
		if (i == null) {
			return "{\"result\":\"FAILED TO SAVE INCIDENT\"}";
		}
		
		return "{\"result\":\"SUCCESS\"}";
//		String res = ORSRequests.getDirections(request.getStart(), request.getEnd());
//		if (res == null) {
//			return "{\"error\":\"PARSE_ERROR\"}";
//		}
	}
	
	@PostMapping(path = "/autocompleteAddress", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PlaceResponse> autocompleteAddress(@RequestBody AddressRequest request) {
		List<PlaceResponse> res = ORSRequests.autocompletePlace(request.getAddress());
		if (res == null) {
			return null;
		}
		
		return res;
	}
	
}
