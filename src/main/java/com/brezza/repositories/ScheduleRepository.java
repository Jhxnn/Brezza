package com.brezza.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID>{

}
