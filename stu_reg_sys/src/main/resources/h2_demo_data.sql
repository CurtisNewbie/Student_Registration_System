-- h2 version of demo data

-- id, name
INSERT INTO faculty VALUES
    (1, 'Faculty of Social Science');
INSERT INTO faculty VALUES
    (2, 'Faculty of Engineering');
INSERT INTO faculty VALUES
    (3, 'Faculty of Business');
    
--- id, name, fac_fk
INSERT INTO school VALUES
    (1, 'Information School', 2),
    (2, 'Management School', 3),
    (3, 'School of Education', 1),
    (4, 'School of Philosophy', 1),
    (5, 'School of Humanity', 1),
    (6, 'School of Electronics and Computer Science', 2);

-- id, firstname, lastname, position
INSERT INTO lecturer VALUES 
    (1, 'John', 'Cena', 'Pricipal Lecturer'),
    (2, 'Apple', 'Juice', 'Senior Lecturer'),
    (3, 'Macoc', 'Fin', 'Junior Lecturer'),
    (4, 'Peter', 'Park', 'Assistant Professor'),
    (5, 'Water', 'Law', 'Professor');

-- id, name, credit
INSERT INTO module VALUES
    (1, 'Intro to Computational Thinking', 15),
    (2, 'Data Structure', 20),
    (3, 'Basic Algorithms', 20),
    (4, 'Memory Management', 15),
    (5, 'Database Design', 15),
    (6, 'Project Management', 15),
    (7, 'Human Resource Management', 15),
    (8, 'Object-Oriented Programming', 15);

-- id, name, credit, sch_fk, lec_fk
INSERT INTO course VALUES
    (1, 'Data Science', 180, 1, 1),
    (2, 'Information Systems', 180, 1, 1),
    (3, 'Electrical engineering', 180, 6, 1),
    (4, 'Computer Science', 180, 6, 5),
    (5, 'Information Security', 180, 6, 5),
    (6, 'Education System', 180, 3, 4);

-- id, firstname, lastname, reg_date, cou_fk
INSERT INTO student VALUES 
    (1, 'Curtis', 'Newbie', '2020-01-21', 2),
    (2, 'Sharon', 'Lalala', '2019-02-01', 1),
    (3, 'Viv', 'Anderson', '2018-03-01', 3),
    (4, 'Will', 'Smith', '1986-05-11', 4),
    (5, 'Bell', 'Dandy', '1986-03-18', 5),
    (6, 'Rock', 'Sandwich', '2016-01-21', 5),
    (7, 'Fiona', 'Loran', '2017-02-20', 6),
    (8, 'Banana', 'Man', '1987-09-08', 1);

-- cou_fk, mod_fk
INSERT INTO course_module VALUES (1 , 2);
INSERT INTO course_module VALUES (1 , 3);
INSERT INTO course_module VALUES (1 , 5);
INSERT INTO course_module VALUES (2 , 1);
INSERT INTO course_module VALUES (2 , 2);
INSERT INTO course_module VALUES (2 , 5);
-- lec_fk, mod_fk
INSERT INTO lecturer_module VALUES (1 , 1);
INSERT INTO lecturer_module VALUES (2 , 3);
INSERT INTO lecturer_module VALUES (3 , 1);
INSERT INTO lecturer_module VALUES (3 , 2);
INSERT INTO lecturer_module VALUES (4 , 2);
INSERT INTO lecturer_module VALUES (5 , 5);
INSERT INTO lecturer_module VALUES (5 , 6);
-- stu_fk, mod_fk
INSERT INTO student_module VALUES (1 , 1);
INSERT INTO student_module VALUES (1 , 2);
INSERT INTO student_module VALUES (1 , 5);
