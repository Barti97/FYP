package com.bartosz.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bartosz.domain.DirectionsRequest;
import com.bartosz.requests.ORSRequests;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@PostMapping(path = "/directions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String directions(@RequestBody DirectionsRequest request) {
		String res = ORSRequests.getDirections(request.getStart(), request.getEnd());
		if (res == null) {
			return "{\"error\":\"PARSE_ERROR\"}";
		}
		return "{\"result\":" + res + "}";
	}
	
}
