package com.brezza.models;

import java.time.LocalDate;
import java.util.UUID;

import com.brezza.models.enums.InspectionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	private LocalDate date;
	
	private InspectionStatus status;
	
    @ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "user_id")
	private User owner;
	
	private String observations;

	public UUID getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(UUID inspectionId) {
		this.inspectionId = inspectionId;
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}


	
	

}
