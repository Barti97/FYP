package com.bartosz;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bartosz.dao.RoleDAO;
import com.bartosz.domain.Incident;
import com.bartosz.domain.Role;
import com.bartosz.domain.User;
import com.bartosz.service.IncidentService;
import com.bartosz.service.UserService;
import com.google.maps.model.LatLng;


@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	PasswordEncoder passEnc;
	
	@Autowired
	IncidentService incidentService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role johnRole = new Role("john@email.com", "ROLE_USER");
		roleDao.save(johnRole);
		Role maryRole = new Role("mary@email.com", "ROLE_USER");
		roleDao.save(maryRole);
		Role patRole = new Role("pat@email.com", "ROLE_USER");
		roleDao.save(patRole);
		Role adminRole = new Role("admin@email.com", "ROLE_ADMIN");
		roleDao.save(adminRole);
		
		User john = new User("john@email.com", passEnc.encode("johnPass"), "John", "Johnson", 871234567, LocalDateTime.now(), johnRole, true);
		userService.addUser(john);
		User mary = new User("mary@email.com", passEnc.encode("maryPass"), "Mary", "Cook", 857654321, LocalDateTime.now(), maryRole, true);
		userService.addUser(mary);
		User pat = new User("pat@email.com", passEnc.encode("patPass"), "Pat", "Murphy", 865431267, LocalDateTime.now(), patRole, true);
		userService.addUser(pat);
		User admin = new User("admin@email.com", passEnc.encode("adminPass"), "Admin", "Admin", 123456789, LocalDateTime.now(), adminRole, true);
		userService.addUser(admin);
		
		Incident i1 = new Incident("Breakdown", new LatLng(51.88896136746316, -8.506700992584229), admin);
		incidentService.addIncident(i1);
		Incident i2 = new Incident("Accident", new LatLng(51.87466946011214, -8.519092798233032), admin);
		incidentService.addIncident(i2);
		
	}

}
