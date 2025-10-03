package com.ase.backend.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ase.backend.service.JobIngestionService;

@RestController
public class IngestionController {
	private final JobIngestionService service;

	public IngestionController(JobIngestionService service) {
		super();
		this.service = service;
	}
	@GetMapping("/ingest-now")
    public String ingestNow(@RequestParam(defaultValue = "14") int days) {
        int count = service.ingestSince(LocalDate.now().minusDays(3));
        return "Inserted: " + count;
    }
}
