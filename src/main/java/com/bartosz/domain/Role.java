package com.bartosz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
	
	@Id
	private String userEmail;
	
	@Column
	private String roleDescription;
	
	public String toString() {
		return "User: " + this.userEmail + "\n\tRole: " + this.roleDescription;
	}

}
