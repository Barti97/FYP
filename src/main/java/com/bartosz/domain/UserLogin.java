package com.bartosz.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


public class UserLogin {

	@OneToOne
	@JoinColumn
	private User userId;

	@Column(nullable = false)
	private String password;

	public UserLogin(String password) {

		this.password = password;
	}

	public String toString() {
		String output = "User: " + userId + "\n\tPassword: " + this.password;
		return output;
	}
}
