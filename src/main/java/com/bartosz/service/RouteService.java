package com.bartosz.service;

import java.util.List;

import com.bartosz.domain.Route;

public interface RouteService {
	
	Route findRouteById(int id);
	List<Route> findAllRoutes();
	Route addRoute(Route route);
	boolean removeRoute(int id);
	boolean isRouteInDatabase(int id);

}
