INSERT INTO faculty VALUES
    (1, "Faculty of Social Science"),
    (2, "Faculty of Engineering"),
    (3, "Faculty of Business"),
    (4, "Faculty of Art");
    
INSERT INTO school VALUES
    (1, "Information School", 1),
    (2, "Management School", 1),
    (3, "School of Education", 1),
    (4, "School of Philosophy", 1),
    (6, "School of Humanity", null);

INSERT INTO lecturer VALUES 
    (1, "John", "Cena", "Pricipal Lecturer"),
    (2, "Apple", "Juice", "Senior Lecturer"),
    (3, "to_be_deleted", "someone", "temp staff"),
    (4, "Peter", "Park", "Assistant Professor");

INSERT INTO module VALUES
    (1, "Intro to Computational Thinking", 15),
    (2, "Data Structure", 20),
    (3, "Basic Algorithms", 20),
    (4, "Memory Management", 15);

INSERT INTO course VALUES
    (1, "Data Science", 180, 1, 1),
    (2, "Information Systems", 180, 1, 1),
    (3, "to_be_deleted", 180, 1, 1),
    (4, "Computer Science", 180, 1, 1),
    (5, "Information Security", 180, null, null);

INSERT INTO student VALUES 
    (1, "Curtis", "Newbie", "2020-01-21", 1),
    (2, "Sharon", "Lalala", "2020-02-01", 1),
    (3, "to_be_deleted", "nonono", "2020-03-01", 1),
    (4, "Will", "Smith", "1987-01-01", 1),
    (5, "Banana", "Man", "1987-01-01", null);


INSERT INTO course_module VALUES (1 , 1);
INSERT INTO lecturer_module VALUES (1 , 1);
INSERT INTO student_module VALUES (1 , 1);
INSERT INTO course_module VALUES (2 , 1);
INSERT INTO lecturer_module VALUES (1 , 2);
INSERT INTO student_module VALUES (1 , 2);
