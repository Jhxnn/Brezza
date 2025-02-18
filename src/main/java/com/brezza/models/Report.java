package com.brezza.models;

import java.util.UUID;

import com.brezza.models.enums.Result;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name= "reports")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID reportId;
	
	private Result result;
	
	private String observations;
	

	public UUID getReportId() {
		return reportId;
	}

	public void setReportId(UUID reportId) {
		this.reportId = reportId;
	}

	

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	
	
	
}
