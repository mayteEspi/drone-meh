package com.api.drone.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.drone.ApiDroneApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiDroneApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RouteDroneControllerIT {
	
	@LocalServerPort
	private int port;
	

	private TestRestTemplate restTemplate ;
	private HttpEntity<String> requestEntity;
	private HttpHeaders headers ;
	
	
	@Before
	public void setUp() throws Exception {
		restTemplate = new TestRestTemplate();
		createBasicHeaders();
		requestEntity = new HttpEntity<String>(headers);
	}
	
	@Test
	public void getResidentialsTraceDrone_thenStatus200() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange(
				getUrlWithPort("/residentials"),HttpMethod.GET,requestEntity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));	

	}
	
	private String getUrlWithPort(String uri) {
		return "http://localhost:" + port + "/route" + uri;
	}
	
	private HttpHeaders createBasicHeaders() {
		headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	    return headers;
	}
	
	

}
