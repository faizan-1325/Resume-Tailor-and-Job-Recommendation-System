CREATE TABLE job (
    job_id BIGINT PRIMARY KEY,
    job_role VARCHAR(255),
    job_description VARCHAR(255),
    job_url VARCHAR(255),
    salary BIGINT,
    company_name VARCHAR(255),
    location VARCHAR(255),
    job_type VARCHAR(100),
    posted_date VARCHAR(100)
);