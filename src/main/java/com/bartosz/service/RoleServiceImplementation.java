package com.bartosz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartosz.dao.RoleDAO;
import com.bartosz.domain.Role;

@Service
public class RoleServiceImplementation implements RoleService {
	
	@Autowired
	RoleDAO dao;

	@Override
	public Role findByUserEmail(String email) {
		if (doesUserHaveRole(email)) {
			return dao.findByUserEmail(email);
		}
		return null;
	}

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Role addRole(Role role) {
		if (doesUserHaveRole(role.getUserEmail())) {
			return null;
		}
		return dao.save(role);
	}

	@Override
	public boolean updateRoleDescription(String email, String description) {
		if (doesUserHaveRole(email)) {
			return dao.updateRoleDescription(email, description) == 1;
		}
		return false;
	}

	@Override
	public boolean removeRole(String email) {
		if (doesUserHaveRole(email)) {
			dao.deleteByUserEmail(email);
			return true;
		}
		return false;
	}

	@Override
	public boolean doesUserHaveRole(String email) {
		return dao.existsByUserEmail(email);
	}

}
