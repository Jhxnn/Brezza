package com.brezza.dtos;

import java.util.UUID;

public record PaymentDto(String description, double value, UUID payer) {

}
