package com.packt.microservices.geolocation;

import java.util.List;

public interface GeoLocationService {

	public void create(GeoLocation geoLocation) throws Throwable;

	public List<GeoLocation> findAll();
}
