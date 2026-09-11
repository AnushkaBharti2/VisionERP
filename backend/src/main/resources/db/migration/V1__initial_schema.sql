CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role VARCHAR(30) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE departments (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  description VARCHAR(1000)
);
CREATE TABLE employees (
  id BIGSERIAL PRIMARY KEY,
  employee_code VARCHAR(100) NOT NULL UNIQUE,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(50),
  designation VARCHAR(255),
  joining_date DATE,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  department_id BIGINT REFERENCES departments(id),
  role VARCHAR(30) NOT NULL
);
CREATE TABLE attendance (
  id BIGSERIAL PRIMARY KEY,
  employee_id BIGINT NOT NULL REFERENCES employees(id),
  work_date DATE NOT NULL,
  check_in TIMESTAMP,
  check_out TIMESTAMP,
  status VARCHAR(30),
  source VARCHAR(50),
  work_minutes BIGINT NOT NULL DEFAULT 0,
  CONSTRAINT uq_attendance_employee_date UNIQUE(employee_id, work_date)
);
CREATE INDEX idx_attendance_work_date ON attendance(work_date);
CREATE TABLE visitors (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(50),
  email VARCHAR(255),
  purpose VARCHAR(1000),
  photo_url VARCHAR(1000),
  host_id BIGINT REFERENCES employees(id),
  status VARCHAR(40) NOT NULL,
  check_in_time TIMESTAMP,
  check_out_time TIMESTAMP,
  created_at TIMESTAMP NOT NULL
);
CREATE INDEX idx_visitors_status ON visitors(status);
CREATE TABLE notifications (
  id BIGSERIAL PRIMARY KEY,
  recipient_email VARCHAR(255) NOT NULL,
  type VARCHAR(100) NOT NULL,
  message VARCHAR(2000) NOT NULL,
  read_flag BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP NOT NULL
);
CREATE INDEX idx_notifications_recipient ON notifications(recipient_email, created_at DESC);
CREATE TABLE audit_logs (
  id BIGSERIAL PRIMARY KEY,
  actor_email VARCHAR(255),
  action VARCHAR(100),
  entity_type VARCHAR(100),
  entity_id VARCHAR(100),
  details VARCHAR(2000),
  created_at TIMESTAMP NOT NULL
);
CREATE INDEX idx_audit_created ON audit_logs(created_at DESC);
