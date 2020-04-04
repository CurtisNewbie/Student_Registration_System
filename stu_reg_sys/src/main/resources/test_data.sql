-- Change it with caution! It may break the tests

--    Primary keys used in unit tests:

    -- the id of a instance that should be read-only
        -- READ_ONLY_ID = 1;
    
    -- the id of a instance that is expected to be modified 
        -- MODIFIED_ID = 2;
    
    -- the id of a instance that is expected to be deleted 
        -- DELETED_ID = 3;

    -- the id of a instance that will be created 
        -- CREATED_ID = 10;

INSERT INTO faculty VALUES
    (1, "Faculty of Social Science"),
    (2, "Faculty of Engineering"),
    (3, "Faculty of Business");
    
INSERT INTO school VALUES
    (1, "Information School", 1),
    (2, "Management School", 1),
    (3, "School of Education", 1);

INSERT INTO lecturer VALUES 
    (1, "John", "Cena", "Pricipal Lecturer"),
    (2, "Apple", "Juice", "Senior Lecturer"),
    (3, "to_be_deleted", "someone", "temp staff");

INSERT INTO module VALUES
    (1, "Intro to Computational Thinking", 15),
    (2, "Data Structure", 20),
    (3, "Basic Algorithms", 20);

INSERT INTO course VALUES
    (1, "Data Science", 180, 1, 1),
    (2, "Information Systems", 180, 1, 1),
    (3, "to_be_deleted", 180, 1, 1);

-- the course with id = 2, should always have at least one module
INSERT INTO course_module VALUES (2 , 2);

INSERT INTO student VALUES 
    (1, "Curtis", "Newbie", "2020-01-21", 1),
    (2, "Sharon", "Lalala", "2020-02-01", 1),
    (3, "to_be_deleted", "nonono", "2020-03-01", 1);

