package com.packt.microservices.geolocation;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geolocation")
public class GeoLocationController {

	@Autowired
	private GeoLocationService service;

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> create(@RequestBody GeoLocation geoLocation) {
		try {
			service.create(geoLocation);
			return new ResponseEntity<GeoLocation>(geoLocation, HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<Object>(t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<Map<String, Object>> findAll() throws UnknownHostException {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("locations", service.findAll());
		resultMap.put("Container Host", InetAddress.getLocalHost());
		resultMap.put("Container IP", InetAddress.getLocalHost().getAddress());
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(resultMap,
				HttpStatus.OK);
		return responseEntity;
	}
}
