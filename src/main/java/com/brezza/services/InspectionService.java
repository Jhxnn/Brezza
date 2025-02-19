package com.brezza.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brezza.dtos.InspectionDto;
import com.brezza.models.Inspection;
import com.brezza.repositories.InspectionRepository;
import com.brezza.repositories.UserRepository;

@Service
public class InspectionService {

	
	@Autowired
	InspectionRepository inspectionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Inspection findById(UUID id) {
	    return inspectionRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
	}

	public List<Inspection> findAll() {
	    return inspectionRepository.findAll();
	}

	public List<Inspection> findByOwner(UUID ownerId) {
	    var owner = userRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Cannot be found"));
	    return inspectionRepository.findByOwner(owner);
	}

	public Inspection createInspection(InspectionDto inspectionDto) {
	    var inspection = new Inspection();
	    var owner = userRepository.findById(inspectionDto.userId()).orElseThrow(() -> new RuntimeException("Cannot be found"));
	    BeanUtils.copyProperties(inspectionDto, inspection);
	    inspection.setOwner(owner);
	    return inspectionRepository.save(inspection);
	}

	public Inspection updateInspection(UUID id, InspectionDto inspectionDto) {
	    var inspection = findById(id);
	    var owner = userRepository.findById(inspectionDto.userId()).orElseThrow(() -> new RuntimeException("Cannot be found"));
	    BeanUtils.copyProperties(inspectionDto, inspection);
	    inspection.setOwner(owner);
	    return inspectionRepository.save(inspection);
	}

	public void deleteInspection(UUID id) {
	    var inspection = findById(id);
	    inspectionRepository.delete(inspection);
	}

}
