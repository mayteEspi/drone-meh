package com.api.drone.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.drone.service.ResidentialService;

@RestController
@RequestMapping("/route")
public class RouteDroneController {
	
	private static Log log = LogFactory.getLog(RouteDroneController.class);

	@Autowired
	private ResidentialService residentialService;
	
	@GetMapping("/residentials")
	public ResponseEntity<List<String>> listadoEmpleados(@PathParam("coordinateX") String coordinateX, 
			@PathParam("coordinateY") String coordinateY, @PathParam("range") String range){
		ResponseEntity<List<String>> responseListEntity = null;
		try {
			List<String> residentials = residentialService.getResidentialsByPositionDrone(Double.parseDouble(coordinateX),
					 Double.parseDouble(coordinateY), Integer.parseInt(range));
			if(residentials != null) {
				responseListEntity = new ResponseEntity<List<String>>(residentials,  HttpStatus.OK);
			}else {
				responseListEntity = new ResponseEntity<List<String>>(residentials,  HttpStatus.NO_CONTENT);
			}
		} catch (Exception exception) {
			log.error(exception);
			responseListEntity = new ResponseEntity<>(new ArrayList<>(),  HttpStatus.BAD_REQUEST);
		}
		return responseListEntity;
	}
	

}
