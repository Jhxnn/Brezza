package com.brezza.dtos;

import java.util.UUID;

import com.brezza.models.enums.UserType;

public record UserResponseDto(UUID userId, String name, String email, UserType userType) {

}
