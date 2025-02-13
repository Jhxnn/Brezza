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

import com.brezza.dtos.AuthDto;
import com.brezza.dtos.UserRequestDto;
import com.brezza.dtos.UserResponseDto;
import com.brezza.infra.security.TokenService;
import com.brezza.repositories.UserRepository;
import com.brezza.services.EmailService;
import com.brezza.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenService tokenService;
	
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid AuthDto authDto) {
		return ResponseEntity.ok().body(userService.returnToken(authDto));
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@RequestBody @Valid  UserRequestDto userRequestDto) {
		return ResponseEntity.ok().body(userService.createUser(userRequestDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable(name = "id")UUID id,
			@RequestBody UserRequestDto userRequestDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, userRequestDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(name = "id")UUID id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
