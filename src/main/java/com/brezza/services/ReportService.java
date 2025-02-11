package com.brezza.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brezza.dtos.ReportDto;
import com.brezza.models.Report;
import com.brezza.repositories.ReportRepository;

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
