package com.brezza.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brezza.dtos.UserRequestDto;
import com.brezza.dtos.UserResponseDto;
import com.brezza.models.Users;
import com.brezza.repositories.UserRepository;

@Service
public class UserService {

	
	@Autowired
	UserRepository userRepository;
	
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		var user = new Users();
		BeanUtils.copyProperties(userRequestDto, user);
		userRepository.save(user);
		return new UserResponseDto(user.getUserId(), user.getName(), user.getEmail(), user.getType());
	}
	
	public UserResponseDto findById(UUID id) {
		var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
		return new UserResponseDto(user.getUserId(), user.getName(),user.getEmail(), user.getType());
	}
	
	public List<UserResponseDto> findAll(){
		var users = userRepository.findAll();
		return users.stream()
                .map(user -> new UserResponseDto(user.getUserId(), user.getName(), user.getEmail(), user.getType()))
                .toList();
	}
	
	public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
		var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
		BeanUtils.copyProperties(userRequestDto, user);
		userRepository.save(user);
		return new UserResponseDto(user.getUserId(), user.getName(), user.getEmail(), user.getType());
		
	}
	
	public void deleteUser(UUID id) {
		var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
		userRepository.delete(user);
	}
	
	
	
	
}
