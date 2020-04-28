package com.bartosz.requests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bartosz.domain.Incident;
import com.bartosz.domain.PlaceResponse;
import com.bartosz.service.IncidentService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.model.LatLng;

public class ORSRequests {

	public static String getDirections(LatLng source, LatLng destination, boolean avoidPlaces, String preferenceIn, List<Incident> allIncidents) {
		System.out.println("Preference:" + preferenceIn);
		String url = "https://api.openrouteservice.org/v2/directions/driving-car/geojson";
		String coordinates = "\"coordinates\":[[" + source.lng + "," + source.lat + "],[" + destination.lng + "," + destination.lat + "]]";
		
		String incidents = "";
		if (allIncidents != null && allIncidents.size() > 0) {
			for (Incident i : allIncidents) {
				String incident = "["+i.drawPolygon()+"]" ;
				incidents += incident + ", ";
			}
			incidents = incidents.substring(0, incidents.lastIndexOf(","));
		}
		
		System.out.println(incidents);
		String preference = "\"preference\":\""+ preferenceIn +"\"";
		String param = "";
		
		if(avoidPlaces == true && !incidents.isEmpty()) {
			param = "{" + coordinates + ",\"options\":{\"avoid_polygons\":{\"type\": \"MultiPolygon\",\"coordinates\": [" + incidents + "]}},"+ preference +"}";
		}
		else {
			param = "{" + coordinates + ","+ preference +"}";
		}
				
		System.out.println(param);
		
		String resDirections = OpenRouteServiceAPI.executePost(url, param);
		
		JsonObject jsonObject = JsonParser.parseString(resDirections).getAsJsonObject();
		JsonObject features = jsonObject.getAsJsonArray("features").get(0).getAsJsonObject();
		JsonObject geometry = features.getAsJsonObject("geometry");
		JsonArray coords = geometry.getAsJsonArray("coordinates");
		System.out.println(coords.toString());
		return coords.toString();
	}

//	public static List<LatLng> getStartFinishCoordinates(String startingPoint, String destinationPoint) {
//		ArrayList<LatLng> coords = new ArrayList<>();
//		String startSearch = findPlace(startingPoint);
//		if (startSearch == null) {
//			return null;
//		}
//		LatLng startPoint = parsePlaceSearch(startSearch);
//		coords.add(startPoint);
//		String endSearch = findPlace(destinationPoint);
//		if (endSearch == null) {
//			return null;
//		}
//		LatLng endPoint = parsePlaceSearch(endSearch);
//		coords.add(endPoint);
//		return coords;
//	}

//	public static String findPlace(String searchPhrase) {
//		return OpenRouteServiceAPI.executeGet(
//				"https://api.openrouteservice.org/geocode/autocomplete?api_key=5b3ce3597851110001cf62487948acef386c48808312fcdaf6978e33&text="
//						+ searchPhrase.replace(" ", "%20") + "&boundary.country=IE&layers=address,venue,neighbourhood");
//	}
	

//	public static LatLng parsePlaceSearch(String searchResult) {
//		System.out.println(searchResult);
//		JsonObject json = JsonParser.parseString(searchResult).getAsJsonObject();
//		JsonArray features = json.getAsJsonArray("features");
//		JsonObject feature0 = features.get(0).getAsJsonObject();
//		JsonObject geometry = feature0.getAsJsonObject("geometry");
//		JsonArray coords = geometry.getAsJsonArray("coordinates");
//		return new LatLng(coords.get(1).getAsFloat(), coords.get(0).getAsFloat());
//	}
	
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
    		LatLng coordinates = new LatLng(coords.get(1).getAsFloat(),coords.get(0).getAsFloat());
            addresses.add( new PlaceResponse(label.getAsString(), coordinates));
            
        }
        return addresses;
    }

}
