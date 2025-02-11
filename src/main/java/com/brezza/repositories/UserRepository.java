package com.brezza.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.Users;

public interface UserRepository extends JpaRepository<Users, UUID>{

}
