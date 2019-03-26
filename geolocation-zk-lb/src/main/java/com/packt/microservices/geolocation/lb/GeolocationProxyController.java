package com.packt.microservices.geolocation.lb;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/geolocation")
public class GeolocationProxyController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping
	public String findAll() throws Exception {
		URI serviceUri = ZookeeperServiceDiscovery.getGeolocationServiceUri();
		System.out.println("Proxying GET request to service " + serviceUri + " at path " + request.getRequestURI());
		URI uri = new URI(serviceUri.getScheme(), null, serviceUri.getHost(), serviceUri.getPort(),
				request.getRequestURI(), request.getQueryString(), null);
		return restTemplate.getForEntity(uri, String.class).getBody();
	}

	@PostMapping(path = "")
	public String create(@RequestBody String body) throws Exception {
		URI serviceUri = ZookeeperServiceDiscovery.getGeolocationServiceUri();
		System.out.println("Proxying POST request to service " + serviceUri + " at path " + request.getRequestURI());
		URI uri = new URI(serviceUri.getScheme(), null, serviceUri.getHost(), serviceUri.getPort(),
				request.getRequestURI(), request.getQueryString(), null);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		return restTemplate.exchange(uri, HttpMethod.POST, entity, String.class).getBody();
	}
}
