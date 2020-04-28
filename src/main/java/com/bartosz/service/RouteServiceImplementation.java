package com.bartosz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartosz.dao.RouteDAO;
import com.bartosz.domain.Route;

@Service
public class RouteServiceImplementation implements RouteService{

	@Autowired
	RouteDAO dao;
	
	@Override
	public Route findRouteById(int id) {
		if (isRouteInDatabase(id)) {
			return dao.findRouteByRouteId(id);
		}
		return null;
	}

	@Override
	public List<Route> findAllRoutes() {
		return dao.findAll();
	}
	
	@Override
	public Route addRoute(Route route) {
		if (isRouteInDatabase(route.getRouteId())) {
			return null;
		}
		return dao.save(route);
	}
	
	@Override
	public boolean removeRoute(int id) {
		if (isRouteInDatabase(id)) {
			dao.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isRouteInDatabase(int id) {
		return dao.existsById(id);
	}
	

}
