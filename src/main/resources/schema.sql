CREATE TABLE IF NOT EXISTS college(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE NOT NULL,
	address VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS rector(
	id SERIAL PRIMARY KEY,
	personal_number CHAR(10) UNIQUE,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	college_id INT,
    FOREIGN KEY (college_id) REFERENCES college(id)
);

CREATE TABLE IF NOT EXISTS faculties(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE,
	address VARCHAR(100),
	phone VARCHAR(20),
	email VARCHAR(50),
	college_id INT,
	FOREIGN KEY (college_id) REFERENCES college(id)
);

CREATE TABLE IF NOT EXISTS departments(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE,
	phone VARCHAR(20),
	email VARCHAR(50),
	faculty_id INT,
	FOREIGN KEY (faculty_id) REFERENCES faculties(id)
);

CREATE TABLE IF NOT EXISTS teachers(
	id SERIAL PRIMARY KEY,
	personal_number CHAR(10) UNIQUE,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	department_id INT,
	FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE IF NOT EXISTS programmes(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE,
	description TEXT,
	faculty_id INT,
	FOREIGN KEY (faculty_id) REFERENCES faculties(id)
);

CREATE TABLE IF NOT EXISTS semesters(
	id SERIAL PRIMARY KEY,
	semester_number INT,
	programme_id INT,
	UNIQUE(semester_number, programme_id),
	FOREIGN KEY (programme_id) REFERENCES programmes(id)
);

CREATE TABLE IF NOT EXISTS courses(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE,
	description VARCHAR(200),
	teacher_id INT,
	semester_id INT,
	FOREIGN KEY (teacher_id) REFERENCES teachers(id),
	FOREIGN KEY (semester_id) REFERENCES semesters(id)
);

CREATE TABLE IF NOT EXISTS semester_courses(
	id SERIAL PRIMARY KEY,
	course_id INT,
	semester_id INT,
	FOREIGN KEY (course_id) REFERENCES courses(id),
	FOREIGN KEY (semester_id) REFERENCES semesters(id)
);

CREATE TABLE IF NOT EXISTS students(
	id SERIAL PRIMARY KEY,
	faculty_number VARCHAR(10) UNIQUE,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	programme_id INT,
	FOREIGN KEY (programme_id) REFERENCES programmes(id)
);

CREATE TABLE IF NOT EXISTS courses_accreditations (
	id SERIAL PRIMARY KEY,
	course_id INT,
	teacher_id INT,
	FOREIGN KEY (course_id) REFERENCES courses(id),
	FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

--CREATE TABLE head_of_department (
--	id SERIAL PRIMARY KEY,
--	teacher_id INT UNIQUE,
--	department_id INT UNIQUE,
--	FOREIGN KEY (teacher_id) REFERENCES teachers(id),
--	FOREIGN KEY (department_id) REFERENCES departments(id)
--);

CREATE TABLE IF NOT EXISTS users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(20) UNIQUE NOT NULL,
	password VARCHAR(100) NOT NULL,
	role VARCHAR(30)
);
