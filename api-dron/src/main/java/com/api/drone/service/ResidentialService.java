package com.api.drone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.drone.model.ResidentialModel;

@Service
public interface ResidentialService {


	public List<ResidentialModel> getResidentialsByPositionDrone(String x, String y, String range);

}
