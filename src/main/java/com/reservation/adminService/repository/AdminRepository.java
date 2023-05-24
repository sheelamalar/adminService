package com.reservation.adminService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reservation.adminService.model.BusRoute;

@Repository
public interface AdminRepository extends JpaRepository<BusRoute, Integer> {
	
	@Query(value = "select * from busroute where bus_number  = ?1",  nativeQuery = true)
	BusRoute findByBusNumber(int busNumber);
	
}
