package com.bartosz.domain;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRouteID {

	private User user;
	private Route route;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        UserRouteID userRouteId = (UserRouteID) o;
        return user.getUserId() == userRouteId.getUser().getUserId() &&
        		route.getRouteId() == userRouteId.getRoute().getRouteId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, route);
    }
	
}
