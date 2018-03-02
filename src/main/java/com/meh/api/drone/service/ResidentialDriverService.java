package com.meh.api.drone.service;

import org.springframework.stereotype.Service;

@Service
public interface ResidentialDriverService {

	public String getIdResidential(double x, double y);
	public String getNearbyResidential(String id, String direction);
	
}
