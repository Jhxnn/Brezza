package com.brezza.models;

import java.time.LocalDate;
import java.util.UUID;

import com.brezza.models.enums.InspectionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inspections")
public class Inspection {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID inspectionId;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User inspector;
	
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private InspectionStatus status;
	
	private String observation;

	public UUID getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(UUID inspectionId) {
		this.inspectionId = inspectionId;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public User getInspector() {
		return inspector;
	}

	public void setInspector(User inspector) {
		this.inspector = inspector;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public InspectionStatus getStatus() {
		return status;
	}

	public void setStatus(InspectionStatus status) {
		this.status = status;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	
	
	
	
	

}
