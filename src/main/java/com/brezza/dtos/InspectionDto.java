package com.brezza.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.brezza.models.enums.InspectionStatus;

public record InspectionDto(UUID vehicleId, UUID userId, LocalDate date, InspectionStatus status, String observation) {

}
