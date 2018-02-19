package com.api.drone.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.drone.ApiDroneApplication;
import com.api.drone.service.ResidentialService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiDroneApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RouteDroneControllerIT {
	
	@LocalServerPort
	private int port;
	@MockBean
	private ResidentialService residentialService;
	
	private TestRestTemplate restTemplate ;
	private HttpEntity<String> requestEntity;
	private HttpHeaders headers;

   
	
	@Before
	public void setUp() throws Exception {
		restTemplate = new TestRestTemplate();
		createBasicHeaders();
		requestEntity = new HttpEntity<String>(headers);
	}
	
	@Test
	public void getResidentialsTraceDrone_WithoutParameters_thenStatus500() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange(
				getUrlWithPort("/residentials"),HttpMethod.GET,requestEntity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));	
	}
	
	@Test
	public void getResidentialsTraceDrone_thenStatus200() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(getUrlWithPort("/residentials"))
			    .queryParam("coordinateX", "38.56889")
			    .queryParam("coordinateY", "40.511107")
			    .queryParam("range", "1");
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(),HttpMethod.GET,requestEntity,String.class);
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
