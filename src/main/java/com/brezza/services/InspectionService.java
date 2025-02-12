package com.brezza.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brezza.dtos.InspectionDto;
import com.brezza.models.Inspection;
import com.brezza.models.enums.InspectionStatus;
import com.brezza.repositories.InspectionRepository;
import com.brezza.repositories.UserRepository;

@Service
public class InspectionService {

	
	@Autowired
	InspectionRepository inspectionRepository;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	UserRepository userRepository;
	
	public Inspection findById(UUID id) {
		return inspectionRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
	}
	
	public List<Inspection> findAll(){
		return inspectionRepository.findAll();
	}
	public List<Inspection> findByVehicle(UUID id){
		var vehicle = vehicleService.findById(id);
		return inspectionRepository.findByVehicle(vehicle);
	}
	
	public List<Inspection> findByStatus(InspectionStatus status){
		return inspectionRepository.findByStatus(status);
	}
	
	public List<Inspection> findByInspector(UUID id){
		var inspector = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
		return inspectionRepository.findByInspector(inspector);
	}
	
	public List<Inspection> findByDate(LocalDate date){
		return inspectionRepository.findByDate(date);
	}
	
	public Inspection createInspection(InspectionDto inspectionDto) {
		var inspection = new Inspection();
		var inspector = userRepository.findById(inspectionDto.userId()).orElseThrow(()-> new RuntimeException("Cannot be found"));
		var vehicle = vehicleService.findById(inspectionDto.vehicleId());
		BeanUtils.copyProperties(inspectionDto, inspection);
		inspection.setInspector(inspector);
		inspection.setVehicle(vehicle);
		return inspectionRepository.save(inspection);
	}
	
	public Inspection updateInspection(UUID id, InspectionDto inspectionDto) {
		var inspection = findById(id);
		var vehicle = vehicleService.findById(inspectionDto.vehicleId());
		var inspector = userRepository.findById(inspectionDto.userId()).orElseThrow(()-> new RuntimeException("Cannot be found"));
		BeanUtils.copyProperties(inspectionDto, inspection);
		inspection.setInspector(inspector);
		inspection.setVehicle(vehicle);
		return inspectionRepository.save(inspection);
		
	}
	public void deleteInspection(UUID id) {
		var inspection = findById(id);
		inspectionRepository.delete(inspection);
	}
}
