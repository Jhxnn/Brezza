package com.brezza.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brezza.dtos.VehicleDto;
import com.brezza.models.Vehicle;
import com.brezza.repositories.UserRepository;
import com.brezza.repositories.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Vehicle findById(UUID id) {
		return vehicleRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
	}
	
	public List<Vehicle> findAll(){
		return vehicleRepository.findAll();
	}
	
	public Vehicle createVehicle(VehicleDto vehicleDto) {
		var vehicle = new Vehicle();
		var owner = userRepository.findById(vehicleDto.owner()).orElseThrow(()-> new RuntimeException("Cannot be found"));
		BeanUtils.copyProperties(vehicleDto, vehicle);
		vehicle.setOwner(owner);
		return vehicleRepository.save(vehicle);
	}
	public Vehicle updateVehicle(UUID id, VehicleDto vehicleDto) {
		var vehicle= findById(id);
		var owner = userRepository.findById(vehicleDto.owner()).orElseThrow(()-> new RuntimeException("Cannot be found"));
		BeanUtils.copyProperties(vehicleDto, vehicle);
		vehicle.setOwner(owner);
		return vehicleRepository.save(vehicle);
		
	}
	public void deleteVehicle(UUID id) {
		var vehicle= findById(id);
		vehicleRepository.delete(vehicle);
	}
}
