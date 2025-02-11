package com.brezza.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.brezza.models.enums.ScheduleStatus;

public record ScheduleDto(LocalDate date, UUID userId, UUID vehicleId,ScheduleStatus status) {

}
