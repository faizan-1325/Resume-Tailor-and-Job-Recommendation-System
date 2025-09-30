CREATE database ResumeTailor;

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

ALTER TABLE job
  ADD COLUMN source VARCHAR(100) NOT NULL,
  ADD COLUMN external_id VARCHAR(191) NOT NULL;

CREATE UNIQUE INDEX ux_job_source_externalid ON job (source, external_id);

-- (Optional but recommended for “last 3 days” queries)
CREATE INDEX ix_job_posted_date ON job (posted_date);

