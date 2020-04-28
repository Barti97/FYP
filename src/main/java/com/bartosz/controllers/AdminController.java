package com.bartosz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bartosz.domain.User;
import com.bartosz.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;

	@GetMapping(value= {"/", "/home"})
	public String handleIndexRequest(Model model) { //, Principal user
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
//		User loggedInUser = userService.findByEmail(user.getName());
//		model.addAttribute("user", loggedInUser);
//		List<Job> jobs = jobService.findAllJobs();
//		model.addAttribute("jobs", jobs);
//		SearchForm searchForm = new SearchForm();
//		model.addAttribute("searchForm", searchForm);
		return "home";
	}
}
