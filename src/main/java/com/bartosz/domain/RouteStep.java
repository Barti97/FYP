package com.bartosz.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteStep {

	private float distance;
	private float duration;
	private byte type;
	private String instruction;
	private String name;
	private List<Integer> waypoints;
	
	public String toString() {
		return this.instruction;
	}
}
