package com.bartosz.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import com.bartosz.domain.Role;
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
	private LocalDateTime DoB;

	@Column(nullable=false)
	private String password;

	@Column(nullable = false, unique = true)
	@Email
	private String email;

	@Column(nullable = false, unique = true)
	private int phoneNumber;
	
	@OneToOne
	@JoinColumn
	private Role userRole;

	@Column(nullable = false)
	private boolean enabled;

	public User(String email, String password, String name, String surname, int phoneNumber, LocalDateTime dob, Role role, boolean enabled) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.DoB = dob;
		this.userRole = role;
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
