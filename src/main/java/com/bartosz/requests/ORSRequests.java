package com.bartosz.requests;

import java.util.ArrayList;
import java.util.List;

import com.bartosz.domain.Coordinates;
import com.bartosz.domain.PlaceResponse;
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
		
		String url = "https://api.openrouteservice.org/v2/directions/driving-car/geojson";
		String coordinates = "\"coordinates\":[[" + start.lng + "," + start.lat + "],[" + end.lng + "," + end.lat + "]]";
		String incident = "{\"type\": \"Polygon\",\"coordinates\": [[[-8.491230010986328,51.87557691274962],[-8.489534854888916,51.87557691274962],[-8.489534854888916,51.87612005082275],[-8.491230010986328,51.87612005082275],[-8.491230010986328,51.87557691274962]]]}";
		
		String param = "{" + coordinates + ",\"options\":{\"avoid_polygons\":" + incident + "}}";
		
		System.out.println(param);
		
		String resDirections = OpenRouteServiceAPI.executePost(url, param);
		
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
				"https://api.openrouteservice.org/geocode/autocomplete?api_key=5b3ce3597851110001cf62487948acef386c48808312fcdaf6978e33&text="
						+ searchPhrase.replace(" ", "%20") + "&boundary.country=IE&layers=address,venue,neighbourhood");
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
	
	public static List<PlaceResponse> autocompletePlace(String searchPhrase) {
        String searchRes = OpenRouteServiceAPI.executeGet("https://api.openrouteservice.org/geocode/autocomplete?api_key=5b3ce3597851110001cf62487948acef386c48808312fcdaf6978e33&text=" 
        												   + searchPhrase.replace(" ", "%20") + "&boundary.country=IE&layers=address,venue,neighbourhood");

        return parseAutocompleteSearch(searchRes);
    }

    public static List<PlaceResponse> parseAutocompleteSearch(String searchResult) {

    	List<PlaceResponse> addresses = new ArrayList<>();
        
        JsonObject json = JsonParser.parseString(searchResult).getAsJsonObject();
        JsonArray features = json.getAsJsonArray("features");
        
        for (JsonElement jsonElement : features) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject geometry = jsonObject.getAsJsonObject("geometry");
    		JsonArray coords = geometry.getAsJsonArray("coordinates");
    		JsonElement label = jsonObject.getAsJsonObject("properties").get("label");
    		
//    		for (JsonElement coord : coords) {
//    			System.out.println(coord.toString());
//    			
//    		}
    		
    		Coordinates coordinates = new Coordinates(coords.get(1).getAsFloat(),coords.get(0).getAsFloat());
            addresses.add( new PlaceResponse(label.toString(), coordinates));
            
        }
        return addresses;
    }

}
