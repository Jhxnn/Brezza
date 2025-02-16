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

import com.brezza.dtos.ScheduleDto;
import com.brezza.models.Schedule;
import com.brezza.services.ScheduleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Operation(description = "Lista todas as agendas")
    @GetMapping
    public ResponseEntity<List<Schedule>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    @Operation(description = "Lista agenda pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findById(id));
    }

    @Operation(description = "Cria uma agenda")
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(scheduleDto));
    }

    @Operation(description = "Atualiza agenda")
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable(name = "id") UUID id,
                                                   @RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(id, scheduleDto));
    }

    @Operation(description = "Deleta uma agenda")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable(name = "id") UUID id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}

