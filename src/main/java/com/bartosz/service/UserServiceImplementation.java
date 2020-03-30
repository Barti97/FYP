package com.bartosz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartosz.dao.UserDAO;
import com.bartosz.domain.User;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserDAO dao;

	@Override
	public User findById(int id) {
		if (isUserInDatabase(id)) {
			return dao.findById(id).get();
		}
		return null;
	}

	@Override
	public User findByEmail(String email) {
		if (isUserInDatabase(email)) {
			return dao.findByEmail(email);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public List<User> findAllActiveUsers() {
		return dao.findAllByEnabled(true);
	}

	@Override
	public List<Integer> findAllUserIds() {
		return dao.findAllUserIds();
	}

	@Override
	public List<String> findAllUserEmails() {
		return dao.findAllEmails();
	}

	@Override
	public User addUser(User user) {
		if (isUserInDatabase(user.getEmail())) {
			return null;
		}
		return dao.save(user);
	}

	@Override
	public boolean removeUser(int id) {
		if (isUserInDatabase(id)) {
			dao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserName(int id, String name) {
		if (isUserInDatabase(id)) {
			return dao.updateUserName(id, name) == 1;
		}
		return false;
	}

	@Override
	public boolean updateUserSurname(int id, String surname) {
		if (isUserInDatabase(id)) {
			return dao.updateUserSurname(id, surname) == 1;
		}
		return false;
	}

	@Override
	public boolean updateUserPhoneNumber(int id, int phoneNumber) {
		if (isUserInDatabase(id)) {
			return dao.updateUserPhoneNumber(id, phoneNumber) == 1;
		}
		return false;
	}

	@Override
	public boolean updateUserEnabled(int id, boolean enabled) {
		if (isUserInDatabase(id)) {
			return dao.updateUserEnabled(id, enabled) == 1;
		}
		return false;
	}

	@Override
	public boolean isUserInDatabase(int id) {
		return dao.existsById(id);
	}

	@Override
	public boolean isUserInDatabase(String email) {
		return dao.existsByEmail(email);
	}

}
