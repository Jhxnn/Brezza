package com.brezza.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID>{

}
