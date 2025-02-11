package com.brezza.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.Report;

public interface ReportRepository extends JpaRepository<Report, UUID> {

}
