package com.bartosz.domain;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class UserRoutes {

	@OneToOne
	@JoinColumn
	private User userId;

	@OneToMany
	@JoinColumn
	private Routes routeId;

	public String toString() {
		String output = "User: " + userId + "\n\tRoute: " + this.routeId;
		return output;
	}
}
