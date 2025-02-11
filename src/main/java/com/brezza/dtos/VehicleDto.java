package com.brezza.dtos;

import java.util.UUID;

public record VehicleDto(String licensePlate, String chassis, String brand, String model, int manufacturingYear, String color, UUID owner) {

}
