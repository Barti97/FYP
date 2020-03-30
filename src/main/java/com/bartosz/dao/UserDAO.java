package com.bartosz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bartosz.domain.User;

public interface UserDAO extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);
	User findByEmail(String email);
	List<User> findAllByEnabled(boolean enabled);
	
	@Query(value="SELECT userId FROM user", nativeQuery=true)
	List<Integer> findAllUserIds();
	
	@Query(value="SELECT email FROM user", nativeQuery=true)
	List<String> findAllEmails();
	
	@Modifying
	@Transactional
	@Query(value="UPDATE user SET name= :name WHERE userId= :id", nativeQuery= true)
	int updateUserName(@Param("id") int id, @Param("name") String name);

	@Modifying
	@Transactional
	@Query(value="UPDATE user SET surname= :surname WHERE userId= :id", nativeQuery= true)
	int updateUserSurname(@Param("id") int id, @Param("surname") String surname);

	@Modifying
	@Transactional
	@Query(value="UPDATE user SET phoneNumber= :phoneNumber WHERE userId= :id", nativeQuery= true)
	int updateUserPhoneNumber(@Param("id") int id, @Param("phoneNumber") int phoneNumber);

	@Modifying
	@Transactional
	@Query(value="UPDATE user SET enabled= :enabled WHERE userId= :id", nativeQuery= true)
	int updateUserEnabled(@Param("id") int id, @Param("enabled") boolean enabled);

}
