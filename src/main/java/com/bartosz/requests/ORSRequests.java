package com.bartosz.requests;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.model.LatLng;

public class ORSRequests {

	public static String getDirections(String source, String destination) {
		List<LatLng> startFinishCoords = getStartFinishCoordinates(source, destination);
		if (startFinishCoords == null) {
			return null;
		}
		ArrayList<LatLng> waypoints = new ArrayList<>();
		LatLng start = startFinishCoords.get(0);
		LatLng end = startFinishCoords.get(1);
		String resDirections = OpenRouteServiceAPI.executePost(
				"https://api.openrouteservice.org/v2/directions/driving-car/geojson",
				"{\"coordinates\":[[" + start.lng + "," + start.lat + "],[" + end.lng + "," + end.lat + "]]}");
		JsonObject jsonObject = JsonParser.parseString(resDirections).getAsJsonObject();
		JsonObject features = jsonObject.getAsJsonArray("features").get(0).getAsJsonObject();
		JsonObject geometry = features.getAsJsonObject("geometry");
		JsonArray coords = geometry.getAsJsonArray("coordinates");
		for (JsonElement j : coords) {
			JsonArray c = j.getAsJsonArray();
			waypoints.add(new LatLng(c.get(1).getAsFloat(), c.get(0).getAsFloat()));
		}
		return coords.toString();
	}

	public static List<LatLng> getStartFinishCoordinates(String startingPoint, String destinationPoint) {
		ArrayList<LatLng> coords = new ArrayList<>();
		String startSearch = findPlace(startingPoint);
		if (startSearch == null) {
			return null;
		}
		LatLng startPoint = parsePlaceSearch(startSearch);
		coords.add(startPoint);
		String endSearch = findPlace(destinationPoint);
		if (endSearch == null) {
			return null;
		}
		LatLng endPoint = parsePlaceSearch(endSearch);
		coords.add(endPoint);
		return coords;
	}

	public static String findPlace(String searchPhrase) {
		return OpenRouteServiceAPI.executeGet(
				"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf62487948acef386c48808312fcdaf6978e33&text="
						+ searchPhrase.replace(" ", "%20") + "&boundary.country=IE&layers=venue&size=1");
	}

	public static LatLng parsePlaceSearch(String searchResult) {
		System.out.println(searchResult);
		JsonObject json = JsonParser.parseString(searchResult).getAsJsonObject();
		JsonArray features = json.getAsJsonArray("features");
		JsonObject feature0 = features.get(0).getAsJsonObject();
		JsonObject geometry = feature0.getAsJsonObject("geometry");
		JsonArray coords = geometry.getAsJsonArray("coordinates");
		return new LatLng(coords.get(1).getAsFloat(), coords.get(0).getAsFloat());
	}

}
