package com.brezza.controllers;

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
import com.brezza.services.InspectionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping
public class InspectionController {
    @Autowired
    InspectionService inspectionService;

    
    @Operation(description = "Lista todas as vistorias")
    @GetMapping
    public ResponseEntity<List<Inspection>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findAll());
    }

    
    @Operation(description = "Lista vistoria pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Inspection> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findById(id));
    }
    
    @Operation(description = "Lista vistoria pelo dono")
    @GetMapping("/owner/{id}")
    public ResponseEntity<List<Inspection>> findByOwner(@PathVariable(name = "id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(inspectionService.findByOwner(id));
    }
    
    @Operation(description = "Cria uma vistoria")
    @PostMapping
    public ResponseEntity<Inspection> createInspection(@RequestBody InspectionDto inspectionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inspectionService.createInspection(inspectionDto));
    }

    @Operation(description = "Atualiza uma vjstkrja")
    @PutMapping("/{id}")
    public ResponseEntity<Inspection> updateInspection(@PathVariable(name = "id") UUID id,
                                                 @RequestBody InspectionDto inspectionDto) {
        return ResponseEntity.status(HttpStatus.OK).body(inspectionService.updateInspection(id, inspectionDto));
    }

    @Operation(description = "Deleta uma vistoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable(name = "id") UUID id) {
        inspectionService.deleteInspection(id);
        return ResponseEntity.noContent().build();
    }


}
