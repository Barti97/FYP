package com.bartosz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bartosz.domain.RouteStep;
import com.bartosz.requests.OpenRouteServiceAPI;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SpringBootApplication
public class FypApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(FypApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//51.870362, -8.472972
		//52.150164, -8.657991
		
//		String res = OpenRouteServiceAPI.executePost(
//				"https://api.openrouteservice.org/v2/directions/driving-car/geojson",
//				"{\"coordinates\":[[-8.472972,51.870362],[-8.657991,52.150164]],"
//						+ "\"options\":{\"avoid_polygons\":{\"type\":\"Polygon\",\"coordinates\":[[[-8.6566687,52.1353188],[-8.6566901,52.1353188],[-8.6539221,52.1352266],[-8.653965,52.1341466],[-8.6566687,52.1342256],[-8.6566687,52.1353188]]]}}"
//						+ "}");
//		
//		System.out.println(res);
	}

}
