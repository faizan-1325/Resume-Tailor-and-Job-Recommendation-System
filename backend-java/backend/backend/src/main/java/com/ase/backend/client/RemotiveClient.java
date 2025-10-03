package com.ase.backend.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ase.backend.bean.Job;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class RemotiveClient implements JobProviderClient{
	private final RestTemplate http=new RestTemplate();
	private final ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public String getSource() {
		return "remotive";
	}

	@Override
	public List<Job> fetchSince(LocalDate since) {
		String url = "https://remotive.com/api/remote-jobs?limit=200";
		String body=http.getForObject(url, String.class);
		List<Job>out=new ArrayList<>();
		try {
			JsonNode root= mapper.readTree(body);
			for(JsonNode n: root.withArray("jobs")) {
				String pub = n.path("publication_date").asText(null);
				if(pub==null||pub.isBlank())continue;
				LocalDate pubDate = LocalDateTime.parse(
				        pub,
				        DateTimeFormatter.ISO_LOCAL_DATE_TIME
				).toLocalDate();
				if(pubDate.isBefore(since))continue; 
				Job j=new Job();
				j.setSource("remotive");
				j.setExternalId(String.valueOf(n.path("id").asLong()));
                j.setJobRole(n.path("title").asText(null));
                j.setJobDescription(n.path("description").asText(null)); // HTML string
                j.setJobURL(n.path("url").asText(null));
                j.setCompanyName(n.path("company_name").asText(null));
                j.setLocation(n.path("candidate_required_location").asText(null));
                j.setJobType(n.path("job_type").asText(null));
                j.setPostedDate(pubDate.toString());
                String salaryStr=n.path("salary").asText("");
                j.setSalary(parseSalaryToLong(salaryStr));
                out.add(j);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("[Remotive] calling API...");
		System.out.println("[Remotive] kept after date filter: " + out.size() + " since=" + since);
		return out; 
	}
	private long parseSalaryToLong(String s) {
        if (s == null || s.isBlank()) return 0L;
        String digits = s.replaceAll("[^0-9]", "");
        if (digits.isEmpty()) return 0L;
        try { return Long.parseLong(digits); } catch (NumberFormatException e) { return 0L; }
    }

}
