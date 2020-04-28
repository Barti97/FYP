package com.bartosz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bartosz.domain.Route;

public interface RouteDAO  extends JpaRepository<Route, Integer> {
	
	Route findRouteByRouteId(int routeID);

	@Query(value="SELECT * FROM route WHERE owner= :userID", nativeQuery=true)
	List<Route> findRoutesByOwner(@Param("userID") int userID);
	
	@Query(value="SELECT routeId FROM route", nativeQuery=true)
	List<Integer> findAllRouteIds();

	List<Route> findAll();


}
