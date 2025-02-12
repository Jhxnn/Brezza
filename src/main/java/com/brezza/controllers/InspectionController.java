package com.brezza.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

import com.brezza.dtos.InspectionDto;
import com.brezza.models.Inspection;
import com.brezza.models.enums.InspectionStatus;
import com.brezza.services.InspectionService;

@RestController
@RequestMapping("/inspection")
public class InspectionController {

    @Autowired
    InspectionService inspectionService;

    @GetMapping
    public ResponseEntity<List<Inspection>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inspection> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findById(id));
    }
    
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<List<Inspection>> findByVehicle(@PathVariable(name = "id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByVehicle(id));
    }
    
    @GetMapping("/inspector/{id}")
    public ResponseEntity<List<Inspection>> findByInspector(@PathVariable(name ="id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByInspector(id));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Inspection>> findByStatus(@PathVariable(name = "status")InspectionStatus status){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByStatus(status));
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Inspection>> findByDate (@PathVariable(name = "id")LocalDate date){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByDate(date));
    }
    
    @PostMapping
    public ResponseEntity<Inspection> createInspection(@RequestBody InspectionDto inspectionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inspectionService.createInspection(inspectionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inspection> updateInspection(@PathVariable(name = "id") UUID id,
                                                       @RequestBody InspectionDto inspectionDto) {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.updateInspection(id, inspectionDto));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable(name = "id") UUID id) {
        inspectionService.deleteInspection(id);
        return ResponseEntity.noContent().build();
    }
}

