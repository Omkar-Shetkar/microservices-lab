package com.packt.microservices.geolocation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

	@Autowired
	private GeoLocationRepository repository;

	@Override
	public void create(GeoLocation geoLocation) throws Throwable {
		repository.addGeoLocation(geoLocation);
	}

	@Override
	public List<GeoLocation> findAll() {
		return repository.getGeoLocations();
	}

}
