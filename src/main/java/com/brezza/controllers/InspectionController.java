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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/inspection")
public class InspectionController {

    @Autowired
    InspectionService inspectionService;

    
    @Operation(description = "Lista todas as vistorias")
    @GetMapping
    public ResponseEntity<List<Inspection>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findAll());
    }

    @Operation(description = "Lista vistoria por id")
    @GetMapping("/{id}")
    public ResponseEntity<Inspection> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findById(id));
    }
    
    @Operation(description = "Lista vistoria por veiculo")
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<List<Inspection>> findByVehicle(@PathVariable(name = "id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByVehicle(id));
    }
    
    @Operation(description = "Lista vistoria por vistoriador")
    @GetMapping("/inspector/{id}")
    public ResponseEntity<List<Inspection>> findByInspector(@PathVariable(name ="id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByInspector(id));
    }
    
    
    @Operation(description = "Lista vistoria por status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Inspection>> findByStatus(@PathVariable(name = "status")InspectionStatus status){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByStatus(status));
    }
    
    @Operation(description = "Lista vistoria por data")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Inspection>> findByDate (@PathVariable(name = "id")LocalDate date){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByDate(date));
    }
    
    @Operation(description = "Cria vistoria")
    @PostMapping
    public ResponseEntity<Inspection> createInspection(@RequestBody InspectionDto inspectionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inspectionService.createInspection(inspectionDto));
    }

    @Operation(description = "Atualiza vistoria")
    @PutMapping("/{id}")
    public ResponseEntity<Inspection> updateInspection(@PathVariable(name = "id") UUID id,
                                                       @RequestBody InspectionDto inspectionDto) {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.updateInspection(id, inspectionDto));
    }
    
    @Operation(description = "Deleta Vistoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable(name = "id") UUID id) {
        inspectionService.deleteInspection(id);
        return ResponseEntity.noContent().build();
    }
}

