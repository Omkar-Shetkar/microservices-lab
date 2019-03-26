package com.packt.microservices.geolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeoLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoLocationApplication.class, args);
		new Zookeeper("192.168.0.4", 2181).register();
	}
}
