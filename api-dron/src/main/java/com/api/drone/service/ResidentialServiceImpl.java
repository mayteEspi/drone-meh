package com.api.drone.service;

import java.util.ArrayList;
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
		Set<String> residentials = new HashSet<>();
		try {
			String idCenter = residentialDriver.getIdResidential(x, y);
			PositionDroneModel position = new PositionDroneModel(
					getResidentialForRangeAndDirectionFromId(residentials, idCenter, range, DroneDirectionEnum.UP),
					getResidentialForRangeAndDirectionFromId(residentials, idCenter, range, DroneDirectionEnum.DOWN),
					getResidentialForRangeAndDirectionFromId(residentials, idCenter, range, DroneDirectionEnum.LEFT),
					getResidentialForRangeAndDirectionFromId(residentials, idCenter, range, DroneDirectionEnum.RIGHT));
			getAllResidentialsByPosition(residentials,range,position);
		}catch (Exception e) {
			log.error("Errpr calling getResidentialsByPositionDrone: " + e.getMessage());
			throw e;
		}
		return new ArrayList<String>(residentials);
	}

	private String getResidentialForRangeAndDirectionFromId(Set<String> residentials, String idResidential, int range, DroneDirectionEnum up) {
		 String codeDirection = idResidential;
	     for (int i = 0; i < range; i++) {
	    	 codeDirection = residentialDriver.getNearbyResidential(codeDirection, up.name());
	     }
	     residentials.add(codeDirection);
	     return codeDirection;
	}
	
	private void getAllResidentialsByPosition(Set<String> residentials, int range, PositionDroneModel position) {
		residentials.addAll(getAllResidentialForRangeAndDirectionFromId(position.getUp(), range, DroneDirectionEnum.LEFT));
		residentials.addAll(getAllResidentialForRangeAndDirectionFromId(position.getUp(), range, DroneDirectionEnum.RIGHT));
		residentials.addAll(getAllResidentialForRangeAndDirectionFromId(position.getDown(), range, DroneDirectionEnum.LEFT));
        residentials.addAll(getAllResidentialForRangeAndDirectionFromId(position.getDown(), range, DroneDirectionEnum.RIGHT));
        residentials.addAll(getAllResidentialForRangeAndDirectionFromId(position.getLeft(), range, DroneDirectionEnum.UP));
        residentials.addAll(getAllResidentialForRangeAndDirectionFromId(position.getLeft(), range, DroneDirectionEnum.DOWN));
        residentials.addAll(getAllResidentialForRangeAndDirectionFromId(position.getRight(), range, DroneDirectionEnum.UP));
        residentials.addAll(getAllResidentialForRangeAndDirectionFromId(position.getRight(), range, DroneDirectionEnum.DOWN));
	}
	
	private Set<String> getAllResidentialForRangeAndDirectionFromId(String residential,int range, DroneDirectionEnum position){
		Set<String> residentials = new HashSet<>();
		 String codeDirection = residential;
		for (int i = 0; i < range; i++) {
			codeDirection = residentialDriver.getNearbyResidential(codeDirection, position.name());
			residentials.add(codeDirection);
	    }
	    return residentials;
	}
	


}
