package com.brezza.dtos;

import com.brezza.models.enums.UserType;

public record UserRequestDto(String name, String Email, String password, UserType type) {

}
