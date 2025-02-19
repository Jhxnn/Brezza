package com.brezza.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.Inspection;
import com.brezza.models.User;
import com.brezza.models.Vehicle;


public interface InspectionRepository extends JpaRepository<Inspection, UUID> {

	List<Inspection> findByOwner(User owner);
}
