--INSERT INTO users (username,password) VALUES('alice','$2a$12$S42Fpdtui6Z418lBT2.nmOApca9tOl4ZtTl8gw1dTtLz0tAcRSVuO');
--INSERT INTO users (username,password) VALUES('bob','$2a$12$/eKYJy1EH8tteEyRv0ZX.O0YW0HAAcSG5uzO.yRlqFgxTnBGXZLyS');
--INSERT INTO users (username,password) VALUES('carol','$2a$12$GmCoLXQTzQM.cIHYsunZkuiaiLveL/lh6.UAXCl3aIQKLzbrWJD3a');
--INSERT INTO users (username,password) VALUES('dave','$2a$12$MxmU4VYzO.uypEl1MsFG4.CVg7z/jak7xgLMfLyF0XEJxBUCd2Z4W');

INSERT INTO college (name, address) VALUES('Default College', 'Default Address');

INSERT INTO rector (personal_number, first_name, last_name) VALUES('42345678', 'Ivan', 'Ivanov');

-- Faculties
INSERT INTO faculties (name, address, phone, email)
VALUES('Informatics and Mathematics', 'Sofia 1504', '9876 292', 'informatics@example.com');

INSERT INTO faculties (name, address, phone, email)
VALUES('History', 'Sofia 1504', '9876 292', 'history@example.com');

INSERT INTO faculties (name, address, phone, email)
VALUES('Philosophy', 'Sofia 1504', '9876 292', 'philosophy@example.com');

INSERT INTO faculties (name, address, phone, email)
VALUES('Law', 'Sofia 1504', '9876 292', 'law@example.com');

-- Departments
INSERT INTO departments (name, phone, email, faculty_id)
VALUES('Computer Science', '9876 292', 'computerScience@example.com', 1);

INSERT INTO departments (name, phone, email, faculty_id)
VALUES('Software Engineering', '9876 292', 'softwareEngineering@example.com', 1);

INSERT INTO departments (name, phone, email, faculty_id)
VALUES('Archeology', '9876 292', 'archeology@example.com', 2);

-- Programmes
INSERT INTO programmes (name, description)
VALUES('Informatics', 'If you want to learn programming and stuff...');

INSERT INTO programmes (name, description)
VALUES('Computer Science', 'Same as informatics but courses are in different order');

-- Teachers
INSERT INTO teachers (personal_number, first_name, last_name, department_id)
VALUES('12345678', 'Asen', 'Asenov', 1);

INSERT INTO teachers (personal_number, first_name, last_name, department_id)
VALUES('22345678', 'Boyan', 'Ivanov', 1);

INSERT INTO teachers (personal_number, first_name, last_name, department_id)
VALUES('32345678', 'Vasil', 'Vasilev', 2);

-- Students
INSERT INTO students (faculty_number, first_name, last_name)
VALUES('12345678', 'Aleks', 'Asenov');

INSERT INTO students (faculty_number, first_name, last_name)
VALUES('22345678', 'Bogdan', 'Ivanov');

INSERT INTO students (faculty_number, first_name, last_name)
VALUES('32345678', 'Elon', 'Musk');