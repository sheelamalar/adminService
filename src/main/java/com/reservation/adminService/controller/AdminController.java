package com.reservation.adminService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.adminService.exception.RouteNotFoundException;
import com.reservation.adminService.model.BusRoute;
import com.reservation.adminService.repository.AdminRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminRepository adminRepository;

	/*
	 * CRUD - Create
	 * URL: http://localhost:8114/api/admin/route
	 */
	@PostMapping("/route")
	public BusRoute createRoute(@RequestBody BusRoute route) {
		logger.debug("createRoute: "+route.getBusNumber());
		
		return adminRepository.save(route);
	}
	
	/*
	 * CRUD - Receive
	 * URL: http://localhost:8114/api/admin/routes
	 */
	@GetMapping("/routes")
	public List<BusRoute> getAllRoutes() {
		logger.debug("getAllRoutes ");
		
		return adminRepository.findAll();
	}

	/*
	 * CRUD - Receive
	 * URL: http://localhost:8114/api/admin/route/78
	 */
	@GetMapping("/route/{busNumber}")
	public int getSeats(@PathVariable(value = "busNumber") int busNumber) {
		logger.debug("getSeats: "+busNumber);
		
		return adminRepository.findByBusNumber(busNumber).getSeats();
	}

	/*
	 * CRUD - Update
	 * URL: http://localhost:8114/api/admin/route/78
	 */
	@PutMapping("/route/{busNumber}")
	public ResponseEntity<BusRoute> updateRoute(@PathVariable(value = "busNumber") int busNumber, @RequestBody BusRoute route) {
		logger.debug("updateRoute: "+busNumber);
		
		BusRoute routeFromDB = adminRepository.findByBusNumber(busNumber);

		if(routeFromDB != null) {
			routeFromDB.setBusNumber(route.getBusNumber());
			routeFromDB.setSource(route.getSource());
			routeFromDB.setDestination(route.getDestination());
			routeFromDB.setSeats(route.getSeats());
			routeFromDB.setPrice(route.getPrice());
				
			return new ResponseEntity<BusRoute>(adminRepository.save(routeFromDB), HttpStatus.ACCEPTED);
		} else {
			throw new RouteNotFoundException(String.valueOf(busNumber));			
		}
	}
	
	/*
	 * CRUD - Delete
	 * URL: http://localhost:8114/api/admin/route/89
	 */
	@DeleteMapping("/route/{busNumber}")
	public ResponseEntity<?> deleteRoute(@PathVariable(value = "busNumber") int busNumber) {
		logger.debug("deleteRoute: "+busNumber);
		
		BusRoute route = adminRepository.findByBusNumber(busNumber);

		if(route != null) {
			adminRepository.delete(route);
			return ResponseEntity.ok().build();
		}
	
		return null;
	}
}
