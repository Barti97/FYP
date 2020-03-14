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
		
		
		
	}

}
