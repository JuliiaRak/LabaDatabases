-- 10 statements for insertion
INSERT INTO Departments (department_name) VALUES 
	('Development'), 
    ('Analitics');
INSERT INTO Skills (skill_name) VALUES
    ('Java'),
    ('C++');
INSERT INTO Employees (department_id, first_name, last_name, position, salary) VALUES
    (1, 'John', 'Doe', 'Developer', 60000),
    (2, 'Jane', 'Smith', 'Analitic', 50000);
INSERT INTO Employee_Skills (employee_id, skill_id) VALUES
    (1, 1),
    (2, 2);
INSERT INTO Certifications (employee_id, certification_name, date) VALUES
    (1, 'Java Certification', '2022-01-01'),
    (2, 'C++ Certification', '2022-02-01');
INSERT INTO Vacations (employee_id, start_date, end_date) VALUES
    (1, '2022-06-01', '2024-07-07'),
    (2, '2022-07-01', '2024-08-07');
    
INSERT INTO Clients (client_name) VALUES
    ('ABC Corp'),
    ('XYZ Inc');
INSERT INTO Contacts_Of_Clients (client_id, phone_number, address) VALUES
    (1, '0983725663', '123 Main St'),
    (2, '0674538226', '456 Oak St');

INSERT INTO Projects (project_name, client_id) VALUES
    ('Project A', 1),
    ('Project B', 2);
INSERT INTO Project_Employees (project_id, employee_id, role) VALUES
    (1, 1, 'Lead Developer'),
    (2, 2, 'Data Analitic');
INSERT INTO Meetings (project_id, meeting_date, duration) VALUES
    (1, '2024-05-01', 2),
    (2, '2024-06-01', 1);
INSERT INTO Project_Feedbacks (project_id, feedback_text) VALUES
    (1, 'Great teamwork on Project A'),
    (2, 'Excellent HR support on Project B');
INSERT INTO Tasks (project_id, employee_id, task_name, task_description, status) VALUES
    (1, 1, 'Develop Feature X', 'Code and test Feature X', 'In Progress'),
    (2, 2, 'Analyze the data', 'Analyze the data for the second project', 'Completed');

INSERT INTO Services (service_name, description, cost) VALUES
    ('Consulting', 'General consulting services', 1000),
    ('Training', 'Employee training programs', 1500);
INSERT INTO Project_Services (project_id, service_id) VALUES
    (1, 1),
    (2, 2);


-- 10 statements for updating
UPDATE Employees SET salary = 65000 WHERE id = 1;
UPDATE Projects SET project_name = 'New Development Project' WHERE id = 1;
UPDATE Clients SET client_name = 'XYZ Corporation' WHERE id = 2;
UPDATE Tasks SET status = 'Completed' WHERE id = 1;
UPDATE Employees SET first_name = 'Johnny' WHERE id = 1;
UPDATE Meetings SET duration = 3 WHERE id = 2;
UPDATE Certifications SET certification_name = 'Java Expert Certification' WHERE id = 1;
UPDATE Project_Employees SET role = 'Senior Data Analyst' WHERE project_id = 2 AND employee_id = 2;
UPDATE Services SET cost = 1200 WHERE id = 1;
UPDATE Contacts_Of_Clients SET phone_number = '0998765432' WHERE id = 1;


-- 10 statements for deletions
DELETE FROM Employee_Skills WHERE employee_id = 1 AND skill_id = 1;
DELETE FROM Certifications WHERE id = 1;
DELETE FROM Projects WHERE id = 1;


-- Big Statement to Join All Tables
SELECT *
FROM Employees
JOIN Departments ON Employees.department_id = Departments.id
JOIN Employee_Skills ON Employees.id = Employee_Skills.employee_id
JOIN Skills ON Employee_Skills.skill_id = Skills.id
JOIN Certifications ON Employees.id = Certifications.employee_id
JOIN Vacations ON Employees.id = Vacations.employee_id
JOIN Clients ON Projects.client_id = Clients.id
JOIN Contacts_Of_Clients ON Clients.id = Contacts_Of_Clients.client_id
JOIN Projects ON Project_Employees.project_id = Projects.id
JOIN Project_Employees ON Employees.id = Project_Employees.employee_id
JOIN Meetings ON Projects.id = Meetings.project_id
JOIN Project_Feedbacks ON Projects.id = Project_Feedbacks.project_id
JOIN Tasks ON Projects.id = Tasks.project_id AND Employees.id = Tasks.employee_id
JOIN Services ON Project_Services.service_id = Services.id
JOIN Project_Services ON Projects.id = Project_Services.project_id;


-- 5 statements with left, right, inner, outer joins.
SELECT Employees.*, Departments.department_name
FROM Employees
LEFT JOIN Departments ON Employees.department_id = Departments.id;

SELECT Projects.*, Employees.first_name, Employees.last_name
FROM Projects
RIGHT JOIN Project_Employees ON Projects.id = Project_Employees.project_id
RIGHT JOIN Employees ON Project_Employees.employee_id = Employees.id;

SELECT Projects.*, Employees.first_name, Employees.last_name
FROM Projects
INNER JOIN Project_Employees ON Projects.id = Project_Employees.project_id
INNER JOIN Employees ON Project_Employees.employee_id = Employees.id;

SELECT Projects.*, Employees.first_name, Employees.last_name
FROM Projects
FULL OUTER JOIN Project_Employees ON Projects.id = Project_Employees.project_id
FULL OUTER JOIN Employees ON Project_Employees.employee_id = Employees.id;


-- 7 statements with aggregate functions and group by and without having.
SELECT department_id, AVG(salary) AS avg_salary
FROM Employees
GROUP BY department_id;

SELECT Clients.id, MAX(Projects.project_name) AS latest_project
FROM Clients
LEFT JOIN Projects ON Clients.id = Projects.client_id
GROUP BY Clients.id;

SELECT Clients.id, SUM(Project_Services.cost) AS total_cost
FROM Clients
LEFT JOIN Projects ON Clients.id = Projects.client_id
LEFT JOIN Project_Services ON Projects.id = Project_Services.project_id
GROUP BY Clients.id;


-- 7 statements with aggregate functions and group by and with having.
SELECT Projects.id, COUNT(Tasks.id) AS task_count
FROM Projects
LEFT JOIN Tasks ON Projects.id = Tasks.project_id
GROUP BY Projects.id
HAVING task_count > 5;

SELECT Employees.department_id, MIN(salary) AS min_salary
FROM Employees
GROUP BY Employees.department_id
HAVING min_salary < 50000;


-- 5 alter table
ALTER TABLE Employees ADD COLUMN email VARCHAR(255);
ALTER TABLE Skills MODIFY COLUMN skill_name VARCHAR(50);
ALTER TABLE Meetings DROP COLUMN duration;

