package com.brezza.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.User;
import com.brezza.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID>{
	
	List<Vehicle> findByOwner(User owner);

}
