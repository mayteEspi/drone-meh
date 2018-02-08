package com.api.drone.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.drone.model.ResidentialModel;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/route")
public class RouteDroneController {
	
	private static Log log = LogFactory.getLog(RouteDroneController.class);

	@GetMapping("/residentials")
	public ResponseEntity<List<ResidentialModel>> listadoEmpleados(@PathParam("coordinateX") String coordinateX, 
			@PathParam("coordinateY") String coordinateY, @PathParam("range") String range){
		ResponseEntity<List<ResidentialModel>> responseListEntity = null;
		try {
			responseListEntity = new ResponseEntity<List<ResidentialModel>>(new ArrayList<ResidentialModel>(),  HttpStatus.OK);
		} catch (Exception exception) {
			log.error(exception);
			responseListEntity = new ResponseEntity<List<ResidentialModel>>(new ArrayList<ResidentialModel>(),  HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseListEntity;
	}
	

}
