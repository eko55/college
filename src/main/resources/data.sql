INSERT INTO users (username,password) VALUES('alice','$2a$12$S42Fpdtui6Z418lBT2.nmOApca9tOl4ZtTl8gw1dTtLz0tAcRSVuO');
INSERT INTO users (username,password) VALUES('bob','$2a$12$/eKYJy1EH8tteEyRv0ZX.O0YW0HAAcSG5uzO.yRlqFgxTnBGXZLyS');
INSERT INTO users (username,password) VALUES('carol','$2a$12$GmCoLXQTzQM.cIHYsunZkuiaiLveL/lh6.UAXCl3aIQKLzbrWJD3a');
INSERT INTO users (username,password) VALUES('dave','$2a$12$MxmU4VYzO.uypEl1MsFG4.CVg7z/jak7xgLMfLyF0XEJxBUCd2Z4W');

INSERT INTO college (name, address) VALUES('Default College', 'Default Address');

INSERT INTO rector (personal_number, first_name, last_name) VALUES('42345678', 'Ivan', 'Ivanov');

-- Faculties
INSERT INTO faculties (name, address, phone, email, college_id)
VALUES('Informatics and Mathematics', 'Sofia 1504', '9876 292', 'informatics@example.com', 1);

INSERT INTO faculties (name, address, phone, email, college_id)
VALUES('History', 'Sofia 1504', '9876 292', 'history@example.com', 1);

INSERT INTO faculties (name, address, phone, email, college_id)
VALUES('Philosophy', 'Sofia 1504', '9876 292', 'philosophy@example.com', 1);

INSERT INTO faculties (name, address, phone, email, college_id)
VALUES('Law', 'Sofia 1504', '9876 292', 'law@example.com', 1);

-- Departments
INSERT INTO departments (name, phone, email, faculty_id)
VALUES('Computer Science', '9876 292', 'computerScience@example.com', 1);

INSERT INTO departments (name, phone, email, faculty_id)
VALUES('Software Engineering', '9876 292', 'softwareEngineering@example.com', 1);

INSERT INTO departments (name, phone, email, faculty_id)
VALUES('Archeology', '9876 292', 'archeology@example.com', 2);

-- Programmes
INSERT INTO programmes (name, description, faculty_id)
VALUES('Informatics', 'If you want to learn programming and stuff...', 1);

INSERT INTO programmes (name, description, faculty_id)
VALUES('Computer Science', 'Same as informatics but courses are in different order', 1);

-- Semesters
INSERT INTO semesters (semester_number, programme_id)
VALUES(1, 1);

INSERT INTO semesters (semester_number, programme_id)
VALUES(2, 1);

INSERT INTO semesters (semester_number, programme_id)
VALUES(3, 1);

INSERT INTO semesters (semester_number, programme_id)
VALUES(4, 1);

INSERT INTO semesters (semester_number, programme_id)
VALUES(5, 1);

INSERT INTO semesters (semester_number, programme_id)
VALUES(6, 1);

INSERT INTO semesters (semester_number, programme_id)
VALUES(7, 1);

INSERT INTO semesters (semester_number, programme_id)
VALUES(8, 1);

-- Teachers
INSERT INTO teachers (personal_number, first_name, last_name, department_id)
VALUES('12345678', 'Asen', 'Asenov', 1);

INSERT INTO teachers (personal_number, first_name, last_name, department_id)
VALUES('22345678', 'Boyan', 'Ivanov', 1);

INSERT INTO teachers (personal_number, first_name, last_name, department_id)
VALUES('32345678', 'Vasil', 'Vasilev', 2);

-- Courses
INSERT INTO courses (name, description, teacher_id, semester_id)
VALUES ('Electronics','Some electronic related things will be done', 1, 1);

INSERT INTO courses (name, description, teacher_id, semester_id)
VALUES ('Programming Basics','Declare variable,etc. ', 1, 1);

INSERT INTO courses (name, description, teacher_id, semester_id)
VALUES ('OOP','Learn what is object,class,etc.', 1, 1);

-- Students
INSERT INTO students (faculty_number, first_name, last_name)
VALUES('12345678', 'Aleks', 'Asenov');

INSERT INTO students (faculty_number, first_name, last_name)
VALUES('22345678', 'Bogdan', 'Ivanov');

INSERT INTO students (faculty_number, first_name, last_name)
VALUES('32345678', 'Elon', 'Musk');

-- Courses Accreditations
INSERT INTO courses_accreditations (course_id, teacher_id)
VALUES(1, 1);

INSERT INTO courses_accreditations (course_id, teacher_id)
VALUES(2, 1);

INSERT INTO courses_accreditations (course_id, teacher_id)
VALUES(3, 1);

INSERT INTO courses_accreditations (course_id, teacher_id)
VALUES(1, 2);

INSERT INTO courses_accreditations (course_id, teacher_id)
VALUES(2, 2);