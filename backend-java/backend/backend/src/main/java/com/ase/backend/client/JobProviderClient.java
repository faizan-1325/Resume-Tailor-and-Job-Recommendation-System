package com.ase.backend.client;

import java.time.LocalDate;
import java.util.List;

import com.ase.backend.bean.Job;

public interface JobProviderClient {
	String getSource();
	List<Job>fetchSince(LocalDate since);
}
