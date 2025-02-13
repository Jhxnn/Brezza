package com.brezza.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.brezza.models.Users;

public interface UserRepository extends JpaRepository<Users, UUID>{
	
	UserDetails findByEmail(String email);

}
