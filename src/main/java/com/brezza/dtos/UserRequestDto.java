package com.brezza.dtos;

import com.brezza.models.enums.UserType;

public record UserRequestDto(String name, String email, String password, UserType type) {

}
