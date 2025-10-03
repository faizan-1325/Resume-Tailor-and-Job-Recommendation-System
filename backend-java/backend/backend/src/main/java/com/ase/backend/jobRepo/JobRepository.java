package com.ase.backend.jobRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ase.backend.bean.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	boolean existsBySourceAndExternalId(String source, String externalId);
}
