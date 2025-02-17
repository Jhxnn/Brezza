package com.brezza.services;

import java.sql.Blob;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brezza.dtos.ReportDto;
import com.brezza.models.Report;
import com.brezza.repositories.ReportRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
public class ReportService {

	@Autowired
	ReportRepository reportRepository;


	public Report findById(UUID id) {
	    return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
	}

	public List<Report> findAll() {
	    return reportRepository.findAll();
	}

	public Report createReport(ReportDto reportDto) {
	    var report = new Report();
	    BeanUtils.copyProperties(reportDto, report);
	    return reportRepository.save(report);
	}
	
	public byte[] generatePdf(UUID reportId) {
		var report = findById(reportId);
		try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
		        PdfDocument pdf = new PdfDocument(writer);
		        Document document = new Document(pdf);
				){
			
			document.add(new Paragraph("Result: "  + report.getResult()));
			document.add(new Paragraph("Observations: " + report.getObservations()));
			document.add(new Paragraph("Inspection: " + report.getInspection()));
			document.close();
			return byteArrayOutputStream.toByteArray();
			
		}
		
		catch(Exception e) {
			throw new RuntimeException("Erro ao gerar pdf",e);
		}
		
	
	}

	public Report updateReport(UUID id, ReportDto reportDto) {
	    var report = findById(id);
	    BeanUtils.copyProperties(reportDto, report);
	    return reportRepository.save(report);
	}

	public void deleteReport(UUID id) {
	    var report = findById(id);
	    reportRepository.delete(report);
	}

	
}
