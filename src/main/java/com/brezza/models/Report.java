package com.brezza.models;

import java.sql.Blob;
import java.util.UUID;

import com.brezza.models.enums.Result;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "reports")
public class Report {

	
	private UUID reportId;
	
	private Inspection inspection;
	
	private Result result;
	
	private String observations;
	
	private Blob documentPdf;

	public UUID getReportId() {
		return reportId;
	}

	public void setReportId(UUID reportId) {
		this.reportId = reportId;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
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

	public Blob getDocumentPdf() {
		return documentPdf;
	}

	public void setDocumentPdf(Blob documentPdf) {
		this.documentPdf = documentPdf;
	}
	
	
}
