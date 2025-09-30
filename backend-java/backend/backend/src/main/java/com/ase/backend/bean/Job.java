package com.ase.backend.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;



@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long jobId;
	String jobRole; 
	@Column(name="job_description", length=5000)
	String jobDescription; 
	String jobURL; 
	long salary;
	String companyName;
	String location;
	String jobType; //full-time or part-time
	String postedDate;
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Job(long jobId, String jobRole, String jobDescription, String jobURL, long salary, String companyName,
			String location, String jobType, String postedDate) {
		super();
		this.jobId = jobId;
		this.jobRole = jobRole;
		this.jobDescription = jobDescription;
		this.jobURL = jobURL;
		this.salary = salary;
		this.companyName = companyName;
		this.location = location;
		this.jobType = jobType;
		this.postedDate = postedDate;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getJobURL() {
		return jobURL;
	}
	public void setJobURL(String jobURL) {
		this.jobURL = jobURL;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
}
