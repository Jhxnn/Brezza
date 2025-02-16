package com.brezza.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brezza.dtos.ReportDto;
import com.brezza.models.Report;
import com.brezza.services.ReportService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    
    @Operation(description = "Lista todos os relatorios")
    @GetMapping
    public ResponseEntity<List<Report>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.findAll());
    }

    @Operation(description = "Lista relatorio por id")
    @GetMapping("/{id}")
    public ResponseEntity<Report> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.findById(id));
    }

    @Operation(description = "Gera pdf pelo id do relatorio")
    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable(name = "id")UUID id){
    	byte[] pdfBytes = reportService.generatePdf(id);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf");
	    headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
	    return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}

    @Operation(description = "Cria um relatorio")
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody ReportDto reportDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reportService.createReport(reportDto));
    }

    @Operation(description = "Atualiza um relatorio")
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable(name = "id") UUID id,
                                               @RequestBody ReportDto reportDto) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.updateReport(id, reportDto));
    }

    @Operation(description = "Deleta um relatorio")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable(name = "id") UUID id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}

