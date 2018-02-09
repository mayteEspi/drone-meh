package com.api.drone.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.drone.model.ResidentialModel;
import com.api.drone.service.ResidentialService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/route")
public class RouteDroneController {
	
	private static Log log = LogFactory.getLog(RouteDroneController.class);

	@Autowired
	private ResidentialService residentialService;
	
	@GetMapping("/residentials")
	public ResponseEntity<List<ResidentialModel>> listadoEmpleados(@PathParam("coordinateX") String coordinateX, 
			@PathParam("coordinateY") String coordinateY, @PathParam("range") String range){
		ResponseEntity<List<ResidentialModel>> responseListEntity = null;
		try {
			List<ResidentialModel> residentials = residentialService.getResidentialsByPositionDrone(coordinateX, coordinateY, range);
			if(residentials != null) {
				responseListEntity = new ResponseEntity<List<ResidentialModel>>(residentials,  HttpStatus.OK);
			}else {
				responseListEntity = new ResponseEntity<List<ResidentialModel>>(residentials,  HttpStatus.NO_CONTENT);
			}
		} catch (Exception exception) {
			log.error(exception);
			responseListEntity = new ResponseEntity<List<ResidentialModel>>(new ArrayList<ResidentialModel>(),  HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseListEntity;
	}
	

}
