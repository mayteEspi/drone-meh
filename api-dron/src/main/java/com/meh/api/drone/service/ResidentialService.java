package com.meh.api.drone.service;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface ResidentialService {

	public List<String> getResidentialsByPositionDrone(double x, double y, int range);

}
