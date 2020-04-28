package com.bartosz.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bartosz.dao.RouteDAO;
import com.bartosz.domain.AddressRequest;
import com.bartosz.domain.DirectionsRequest;
import com.bartosz.domain.Incident;
import com.bartosz.domain.IncidentUpdateRequest;
import com.bartosz.domain.IncidentsRequest;
import com.bartosz.domain.LoginRequest;
import com.bartosz.domain.PlaceResponse;
import com.bartosz.domain.Role;
import com.bartosz.domain.Route;
import com.bartosz.domain.User;
import com.bartosz.requests.ORSRequests;
import com.bartosz.service.IncidentService;
import com.bartosz.service.RoleService;
import com.bartosz.service.UserService;
import com.google.maps.model.LatLng;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

	@Autowired
	IncidentService incidentService;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passEnc;
	
	@Autowired
	ServerController serverController;
	
	@Autowired
	RouteDAO dao;

	@PostMapping(path = "/directions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String directions(@RequestBody DirectionsRequest request) {
		return serverController.directions(request.getStart(), request.getEnd(), request.isAvoidIncidents(), request.getPreference());
	}

	@PostMapping(path = "/incident/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveIncident(@RequestBody IncidentsRequest request) {
		
		int reportNumber = 0; 
		
		User sender = userService.findByEmail(request.getUserEmail());
		if (sender == null) {
			return "{\"result\":\"FAILED TO FIND USER\"}";
		}

		Incident i = incidentService.addIncident(new Incident(request.getType(), request.getLocation(), reportNumber, sender));
		if (i == null) {
			return "{\"result\":\"FAILED TO SAVE INCIDENT\"}";
		}

		return "{\"result\":\"SUCCESS\"}";
	}

	@GetMapping(path = "/incident/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Incident> getAllIncidents() {
		return incidentService.findAllIncidents();

	}

	@PostMapping(path = "/autocompleteAddress", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PlaceResponse> autocompleteAddress(@RequestBody AddressRequest request) {
		List<PlaceResponse> res = ORSRequests.autocompletePlace(request.getAddress());
//		for (PlaceResponse pr : res) {
//			System.out.println(pr.getAddress());
//		}
		if (res == null) {
			return null;
		}

		return res;
	}

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestBody LoginRequest request, Principal principal) {
//		System.out.println(principal.getName());
		if (request.getUsername().equals(principal.getName())) {
			return userService.findByEmail(principal.getName());
		} else {
			return null;
		}
	}

	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String register(@RequestBody User user) {
//		System.out.println(user);
		if (!userService.isUserInDatabase(user.getEmail())) {
			Role userRole = roleService.addRole(new Role(user.getEmail(), "ROLE_USER"));
			if (userRole != null) {
				user.setUserRole(userRole);
				user.setEnabled(true);
				user.setPassword(passEnc.encode(user.getPassword()));
				User newUser = userService.addUser(user);
				if (newUser != null) {
					return "success";
				}
			}
		}
		return "error";
	}
	
	@PostMapping(path = "/incident/proximity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Incident checkClosestIncident(@RequestBody LatLng location) {
		return serverController.checkClosestIncident(location);
	}
	
	@PostMapping(path = "/incident/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateIncident(@RequestBody IncidentUpdateRequest request) {
		Incident incident = incidentService.findIncidentById(request.getId());
		if (incident != null) {
			if (request.isIncrease()) {
				incident.setReportNumber(incident.getReportNumber() + 1);
			} else {
				incident.setReportNumber(incident.getReportNumber() - 1);
			}
			if (incidentService.updateIncident(incident.getIncidentId(), incident.getReportNumber())) {
				return "success";
			}
		}
		return "error";
	}
	
	@PostMapping(path = "/route/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveRoute(@RequestBody Route route, Principal principal) {
		System.out.println(route);
		User user = userService.findByEmail(principal.getName());
		if (user != null) {
//		System.out.println(route.getTitle() + route.getStart_lat() + route.getStart_lng());
			route.setOwner(user);
			return serverController.saveRoute(route);
		}
		return "error";
	}
	
	@PostMapping(path = "/route/favourites", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Route> favouriteRoutes(Principal user) {
		System.out.println("FAVOURITE");
		User u = userService.findByEmail(user.getName());
		if (u != null) {
			return dao.findRoutesByOwner(u.getUserId());
		}
		return null;
	}
	

}
