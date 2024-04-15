CREATE TABLE college(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE NOT NULL,
	address VARCHAR(100)
);

CREATE TABLE faculties (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE,
	address VARCHAR(100),
	phone VARCHAR(20),
	email VARCHAR(50),
	college_id INT,
	FOREIGN KEY (college_id) REFERENCES college(id)
);

CREATE TABLE departments (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE,
	phone VARCHAR(20),
	email VARCHAR(50),
	faculty_id INT,
	FOREIGN KEY (faculty_id) REFERENCES faculties(id)
);

CREATE TABLE teachers (
	id SERIAL PRIMARY KEY,
	personal_number CHAR(10) UNIQUE,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	department_id INT,
	FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE courses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE,
	description TEXT,
	teacher_id INT,
	FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE students (
	id SERIAL PRIMARY KEY,
	faculty_number VARCHAR(10) UNIQUE,
	first_name VARCHAR(50),
	last_name VARCHAR(50)
);

CREATE TABLE courses_registrations (
	id SERIAL PRIMARY KEY,
	course_id INT,
	student_id INT,
	FOREIGN KEY (course_id) REFERENCES courses(id),
	FOREIGN KEY (student_id) REFERENCES students(id)
);

CREATE TABLE head_of_department (
	id SERIAL PRIMARY KEY,
	teacher_id INT UNIQUE,
	department_id INT UNIQUE,
	FOREIGN KEY (teacher_id) REFERENCES teachers(id),
	FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(20) UNIQUE NOT NULL,
	password VARCHAR(30) NOT NULL,
	role VARCHAR(30)
);
