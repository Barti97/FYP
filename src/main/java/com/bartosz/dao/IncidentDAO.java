package com.bartosz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bartosz.domain.Incident;
import com.bartosz.domain.User;

public interface IncidentDAO extends JpaRepository<Incident, Integer>{

	boolean existsById(int id);
	List<Incident> findAllByOwner(User user);
	
	List<Incident> findAll();
	
	@Query(value="SELECT incidentId FROM incident", nativeQuery=true)
	List<Integer> findIncidentById();
	
	@Modifying
	@Transactional
	@Query(value="UPDATE incident SET reportNumber= :newNumber WHERE incidentId= :id", nativeQuery= true)
	int updateIncident(@Param("id") int id, @Param("newNumber") int input);
	
}
