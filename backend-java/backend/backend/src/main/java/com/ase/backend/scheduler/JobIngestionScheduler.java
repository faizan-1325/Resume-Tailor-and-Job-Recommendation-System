package com.ase.backend.scheduler;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;

import com.ase.backend.service.JobIngestionService;

public class JobIngestionScheduler {
	private final JobIngestionService service;

	public JobIngestionScheduler(JobIngestionService service) {
		super();
		this.service = service;
	}
	@Scheduled(cron = "0 5 * * * *")
	public void runHourly() {
		LocalDate since = LocalDate.now().minusDays(3);
		int count = service.ingestSince(since);
		System.out.println("[Ingestion] Inserted: " + count + " (since " + since + ")");
	}
}
