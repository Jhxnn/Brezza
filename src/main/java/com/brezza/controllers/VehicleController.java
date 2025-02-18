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

import com.brezza.dtos.VehicleDto;
import com.brezza.models.Vehicle;
import com.brezza.services.VehicleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    
    @Operation(description = "Lista todos os veiculos")
    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findAll());
    }

    
    @Operation(description = "Lista veiculo pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findById(id));
    }
    
    @Operation(description = "Lista veiculo pelo dono")
    @GetMapping("/owner/{id}")
    public ResponseEntity<List<Vehicle>> findByOwner(@PathVariable(name = "id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findByOwner(id));
    }
    
    @Operation(description = "Cria um veiculo")
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.createVehicle(vehicleDto));
    }

    @Operation(description = "Atualiza um veiculo")
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable(name = "id") UUID id,
                                                 @RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.updateVehicle(id, vehicleDto));
    }

    @Operation(description = "Deleta um veiculo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable(name = "id") UUID id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
