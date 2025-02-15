package com.brezza.dtos;

import java.util.UUID;

import com.brezza.models.enums.Result;

public record ReportDto(UUID inspectionId, Result result, String observation, UUID payer) {

}
