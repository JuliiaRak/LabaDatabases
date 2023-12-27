-- Створення бази даних
CREATE DATABASE IF NOT EXISTS ITCompany;

-- Використання бази даних
USE ITCompany;

-- Видалення таблиць
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS Departments;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS Skills;
DROP TABLE IF EXISTS Employee_Skills;
DROP TABLE IF EXISTS Vacations;
DROP TABLE IF EXISTS Certifications;

DROP TABLE IF EXISTS Clients;
DROP TABLE IF EXISTS Contacts_Of_Clients;

DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Project_Employees;
DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Meetings;
DROP TABLE IF EXISTS Project_Feedbacks;

DROP TABLE IF EXISTS Services;
DROP TABLE IF EXISTS Project_Services;
SET foreign_key_checks = 1;

-- Departments
CREATE TABLE IF NOT EXISTS Departments (
    id SERIAL PRIMARY KEY,
    department_name VARCHAR(255)
);

-- Employees
CREATE TABLE IF NOT EXISTS Employees (
    id SERIAL PRIMARY KEY,
    department_id BIGINT UNSIGNED,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    position VARCHAR(255),
    salary DECIMAL(10, 2),
    FOREIGN KEY (department_id) REFERENCES Departments(id)
);

-- Skills
CREATE TABLE IF NOT EXISTS Skills (
    id SERIAL PRIMARY KEY,
    skill_name VARCHAR(255)
);

-- EmployeeSkills
CREATE TABLE IF NOT EXISTS Employee_Skills (
    employee_id BIGINT UNSIGNED,
    skill_id BIGINT UNSIGNED,
    PRIMARY KEY (employee_id, skill_id),
    FOREIGN KEY (employee_id) REFERENCES Employees(id),
    FOREIGN KEY (skill_id) REFERENCES Skills(id)
);

-- Certification
CREATE TABLE IF NOT EXISTS Certifications (
    id SERIAL PRIMARY KEY,
    employee_id BIGINT UNSIGNED,
    certification_name VARCHAR(255),
    date DATE,
    FOREIGN KEY (employee_id) REFERENCES Employees(id)
);

-- EmployeeVacations
CREATE TABLE IF NOT EXISTS Vacations (
    id SERIAL PRIMARY KEY,
    employee_id BIGINT UNSIGNED,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (employee_id) REFERENCES Employees(id)
);

-- Clients
CREATE TABLE IF NOT EXISTS Clients (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(255)
);

-- ContactsOfClients
CREATE TABLE IF NOT EXISTS Contacts_Of_Clients (
    id SERIAL PRIMARY KEY,
    client_id BIGINT UNSIGNED,
    phone_number VARCHAR(20),
    address VARCHAR(255),
    FOREIGN KEY (client_id) REFERENCES Clients(id)
);

-- Projects
CREATE TABLE IF NOT EXISTS Projects (
    id SERIAL PRIMARY KEY,
    project_name VARCHAR(255),
    client_id BIGINT UNSIGNED,
    FOREIGN KEY (client_id) REFERENCES Clients(id)
);

-- ProjectEmployees
CREATE TABLE IF NOT EXISTS Project_Employees (
    project_id BIGINT UNSIGNED,
    employee_id BIGINT UNSIGNED,
    role VARCHAR(255),
    PRIMARY KEY (project_id, employee_id),
    FOREIGN KEY (project_id) REFERENCES Projects(id),
    FOREIGN KEY (employee_id) REFERENCES Employees(id)
);

-- Meetings
CREATE TABLE IF NOT EXISTS Meetings (
    id SERIAL PRIMARY KEY,
    project_id BIGINT UNSIGNED,
    meeting_date DATE,
    duration INT,
    FOREIGN KEY (project_id) REFERENCES Projects(id)
);

-- ProjectFeedbacks
CREATE TABLE IF NOT EXISTS Project_Feedbacks (
    id SERIAL PRIMARY KEY,
    project_id BIGINT UNSIGNED,
    feedback_text TEXT,
    FOREIGN KEY (project_id) REFERENCES Projects(id)
);

-- Tasks
CREATE TABLE IF NOT EXISTS Tasks (
    id SERIAL PRIMARY KEY,
    project_id BIGINT UNSIGNED,
    employee_id BIGINT UNSIGNED,
    task_name VARCHAR(255),
    task_description TEXT,
    status VARCHAR(50),
    FOREIGN KEY (project_id) REFERENCES Projects(id),
    FOREIGN KEY (employee_id) REFERENCES Employees(id)
);

-- Services
CREATE TABLE IF NOT EXISTS Services (
    id SERIAL PRIMARY KEY,
    service_name VARCHAR(255),
    description TEXT,
    cost DECIMAL(10, 2)
);

-- ProjectServices
CREATE TABLE IF NOT EXISTS Project_Services (
    project_id BIGINT UNSIGNED,
    service_id BIGINT UNSIGNED,
    PRIMARY KEY (project_id, service_id),
    FOREIGN KEY (project_id) REFERENCES Projects(id),
    FOREIGN KEY (service_id) REFERENCES Services(id)
);
