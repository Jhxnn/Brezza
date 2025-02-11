package com.brezza.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.Inspection;

public interface InspectionRepository extends JpaRepository<Inspection, UUID>{

}
