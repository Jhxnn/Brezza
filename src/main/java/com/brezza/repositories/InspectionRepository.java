package com.brezza.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.Inspection;
import com.brezza.models.Users;
import com.brezza.models.Vehicle;
import com.brezza.models.enums.InspectionStatus;
import java.time.LocalDate;


public interface InspectionRepository extends JpaRepository<Inspection, UUID>{

	List<Inspection> findByVehicle(Vehicle vehicle);
	
	List<Inspection> findByInspector(Users inspector);
	
	List<Inspection> findByStatus(InspectionStatus status);
	
	List<Inspection> findByDate(LocalDate date);
}
