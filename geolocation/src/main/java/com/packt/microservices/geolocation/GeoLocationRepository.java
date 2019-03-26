package com.packt.microservices.geolocation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class GeoLocationRepository {

	private List<GeoLocation> geoLocations = new ArrayList<>();

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private static final String DATA_FILES_DIR = System.getenv("GEOLOCATION_DATA_FILES_DIR") != null
			? System.getenv("GEOLOCATION_DATA_FILES_DIR")
			: "/opt/packt/geolocation/data";

	public void addGeoLocation(GeoLocation geoLocation) {
		geoLocations.add(geoLocation);
		try {
			MAPPER.writeValue(
					new File(DATA_FILES_DIR + "/user" + geoLocation.getUserId() + "_t" + geoLocation.getTimestamp()),
					geoLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<GeoLocation> getGeoLocations() {
		return Collections.unmodifiableList(geoLocations);
	}
}
