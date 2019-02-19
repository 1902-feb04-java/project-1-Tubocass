CREATE TABLE employees(
	id SERIAL PRIMARY KEY,
	job_title VARCHAR(100),
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	reports_to INTEGER REFERENCES employees(id)
);

CREATE TABLE requests(
	id SERIAL PRIMARY KEY,
	amount MONEY NOT NULL,
	employee_id INTEGER REFERENCES employees(id) NOT NULL,
	status VARCHAR(50), --would prefer an enum type, e.pending, e.accepted, e.rejected
	image BYTEA
);

SELECT * FROM EMPLOYEES;
SELECT * FROM REQUESTS;

INSERT INTO employees (job_title, first_name, last_name) VALUES ('CEO', 'Zaphod', 'Beeblebrox');
INSERT INTO employees (job_title, first_name, last_name, reports_to) VALUES ('COO', 'Zapp', 'Brannigan', 1);
INSERT INTO employees (job_title, first_name, last_name, reports_to) VALUES ('CTO', 'Alan', 'Turing', 1);
INSERT INTO employees (job_title, first_name, last_name, reports_to) VALUES ('Code Monkey', 'Jonathon', 'Blow', 3);
INSERT INTO employees (job_title, first_name, last_name, reports_to) VALUES ('Junior Pinball Wizard', 'Peter', 'Townshend', 2);

INSERT INTO requests (amount, employee_id, status) VALUES (3.50, 4, 'PENDING');