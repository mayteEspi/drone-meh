package com.api.drone.service;

import org.springframework.stereotype.Component;

@Component
public interface ResidentialDriverService {

	public String getIdResidential(double x, double y);
	public String getNearbyResidential(String id, String direction);
	
}
