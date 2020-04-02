-- Change it with caution! It may break the tests
INSERT INTO student VALUES 
    (1, "Curtis", "Newbie", "2020-01-21", NULL),
    (2, "Sharon", "Lalala", "2020-02-01", NULL),
    (3, "to_be_deleted", "nonono", "2020-03-01", NULL);

INSERT INTO lecturer VALUES 
    (1, "John", "Cena", "Pricipal Lecturer"),
    (2, "Apple", "Juice", "Senior Lecturer"),
    (3, "to_be_deleted", "someone", "temp staff");

INSERT INTO course VALUES
    (1, "Computer Science", 180, NULL, NULL),
    (2, "Information Systems", 180, NULL, NULL),
    (3, "Data Science", 180, NULL, NULL);