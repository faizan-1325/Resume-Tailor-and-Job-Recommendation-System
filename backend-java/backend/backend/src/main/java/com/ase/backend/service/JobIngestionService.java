package com.ase.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ase.backend.bean.Job;
import com.ase.backend.client.JobProviderClient;
import com.ase.backend.jobRepo.JobRepository;
@Service
public class JobIngestionService {
	private final List<JobProviderClient> clients;
	private final JobRepository jobRepository;
	public JobIngestionService(List<JobProviderClient> clients, JobRepository jobRepository) {
		super();
		this.clients = clients;
		this.jobRepository = jobRepository;
	}
	@Transactional
	public int ingestSince(LocalDate since) {
		int inserted=0;
		for(JobProviderClient c: clients) {
			List<Job>fetched = c.fetchSince(since);
			List<Job> fresh=new ArrayList<>();
			for(Job j:fetched) {
				if(!jobRepository.existsBySourceAndExternalId(j.getSource(), j.getExternalId())) {
					fresh.add(j);
				}
			}
			jobRepository.saveAll(fresh);
			inserted+=fresh.size();
		}
		return inserted;
	}
}
