package com.api.drone.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.drone.enums.DroneDirectionEnum;
import com.api.drone.model.PositionDroneModel;


@Component
public class ResidentialServiceImpl implements ResidentialService {

	private static Log log = LogFactory.getLog(ResidentialService.class);
	
	@Autowired
	private ResidentialDriverService residentialDriver;
	
	@Override
	public List<String> getResidentialsByPositionDrone(double x, double y, int range){
		List<String> residentialCodes = null;
		try {
			Set<String> residentials = new HashSet<>();
			String idCenter = residentialDriver.getIdResidential(x, y);
			PositionDroneModel position = new PositionDroneModel(
					getResidentialForRangeAndDirectionFromId(residentials, idCenter, range, DroneDirectionEnum.UP),
					getResidentialForRangeAndDirectionFromId(residentials, idCenter, range, DroneDirectionEnum.DOWN),
					getResidentialForRangeAndDirectionFromId(residentials, idCenter, range, DroneDirectionEnum.LEFT),
					getResidentialForRangeAndDirectionFromId(residentials, idCenter, range, DroneDirectionEnum.RIGHT));
			setAllResidentialsReForRangeAndDirectionFromId(residentials,idCenter,range,position);
		}catch (Exception e) {
			log.error("Errpr calling getResidentialsByPositionDrone: " + e.getMessage());
			throw e;
		}
		return residentialCodes;
	}

	private String getResidentialForRangeAndDirectionFromId(Set<String> residentials, String idResidential, int range, DroneDirectionEnum up) {
		 String codeDirection = idResidential;
	     for (int i = 0; i < range; i++) {
	    	 codeDirection = residentialDriver.getNearbyResidential(codeDirection, up.name());
	     }
	     residentials.add(codeDirection);
	     return codeDirection;
	}
	
	private void setAllResidentialsReForRangeAndDirectionFromId(Set<String> residentials, String idCenter, int range, PositionDroneModel position) {
		residentials.addAll(getResidentialsByPosition(idCenter,range, position.getDown()));
		residentials.addAll(getResidentialsByPosition(idCenter,range, position.getUp()));
		residentials.addAll(getResidentialsByPosition(idCenter,range, position.getRight()));
		residentials.addAll(getResidentialsByPosition(idCenter,range, position.getLeft()));
	}
	
	private Set<String> getResidentialsByPosition(String idResidential,int range, String position){
		Set<String> urbs = new HashSet<>();
		 String codeDirection = idResidential;
		for (int i = 0; i < range; i++) {
			codeDirection = residentialDriver.getNearbyResidential(codeDirection, position);
			urbs.add(codeDirection);
	    }
	    return urbs;
	}
	


}
