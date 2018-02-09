package com.api.drone.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.api.drone.model.ResidentialModel;

@Component
public class ResidentialServiceImpl implements ResidentialService {

	private static Log log = LogFactory.getLog(ResidentialService.class);
	
	@Override
	public List<ResidentialModel> getResidentialsByPositionDrone(String x, String y, String range){
		List<ResidentialModel> residentials = null;
		try {
			
		}catch (Exception e) {
			log.error("Errpr calling getResidentialsByPositionDrone: " + e.getMessage());
			throw e;
		}
		return residentials;
	}
}
