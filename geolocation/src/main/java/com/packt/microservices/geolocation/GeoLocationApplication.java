package com.packt.microservices.geolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GeoLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoLocationApplication.class, args);
		// commented out so that the service does not try to connect to zk
//		new Zookeeper("192.168.0.4", 2181).register();
	}
}
