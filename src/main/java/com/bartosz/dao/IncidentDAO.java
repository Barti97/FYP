package com.bartosz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bartosz.domain.Incident;
import com.bartosz.domain.User;

public interface IncidentDAO extends JpaRepository<Incident, Integer>{

	boolean existsById(int id);
	List<Incident> findAllByOwner(User user);
	
	List<Incident> findAll();
	
	@Query(value="SELECT incidentId FROM incident", nativeQuery=true)
	List<Integer> findIncidentById();
	
}
