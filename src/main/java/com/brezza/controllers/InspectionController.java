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

