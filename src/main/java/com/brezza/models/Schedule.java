package com.brezza.models;

import java.time.LocalDate;
import java.util.UUID;

import com.brezza.models.enums.ScheduleStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedules")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;
	
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Users inspector;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	private Vehicle vehicle;
	
	private ScheduleStatus status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Users getInspector() {
		return inspector;
	}

	public void setInspector(Users inspector) {
		this.inspector = inspector;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}
	
	
	
	
	
}
