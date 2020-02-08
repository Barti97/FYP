package com.bartosz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(nullable = false)
	private Date DoB;

	@Column(nullable = false, unique = true)
	@Email
	private String email;

	@Column(nullable = false, unique = true)
	private int phoneNumber;

	@Column(nullable = false)
	private boolean enabled;

	public User(String name, String surname, Date DoB, String email, int phoneNumber, boolean enabled) {
		this.name = name;
		this.surname = surname;
		this.DoB = DoB;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
	}

	public String toString() {
		String output = "User: ";
		if (this.userId != 0) {
			output += this.userId;
		}
		output += "\n\tName: " + this.name + "\n\tSurname: " + this.surname + "\n\tEmail: " + this.email + "\n\tPhone: "
				+ this.phoneNumber + "\n\tActive: ";
		if (this.enabled) {
			output += "Yes";
		} else {
			output += "No";
		}
		return output;
	}

}
