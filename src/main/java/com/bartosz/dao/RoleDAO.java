package com.bartosz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bartosz.domain.Role;

public interface RoleDAO extends JpaRepository<Role, Integer>{
	
	boolean existsByUserEmail(String userEmail);
	Role findByUserEmail(String userEmail);
	
	@Modifying
	@Transactional
	void deleteByUserEmail(String userEmail);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE role SET roleDescription=: roleDescription WHERE userEmail=: userEmail", nativeQuery = true)
	int updateRoleDescription(@Param("userEmail") String userEmail, @Param("roleDescription") String roleDescription);

}
