-- h2 version

CREATE TABLE faculty(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE school(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    fac_fk INT,
    FOREIGN KEY (fac_fk) REFERENCES faculty(id)
);

CREATE TABLE lecturer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL
);

CREATE TABLE module(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    credit SMALLINT NOT NULL
);

CREATE TABLE course(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    credit SMALLINT NOT NULL,
    sch_fk INT,
    lec_fk INT,
    FOREIGN KEY (sch_fk) REFERENCES school(id),
    FOREIGN KEY (lec_fk) REFERENCES lecturer(id)
);

CREATE TABLE lecturer_module(
    lec_fk INT,
    mod_fk INT,
    PRIMARY KEY (lec_fk, mod_fk),
    FOREIGN KEY (lec_fk) REFERENCES lecturer (id),
    FOREIGN KEY (mod_fk) REFERENCES module (id)
);

CREATE TABLE student(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    reg_date DATE NOT NULL,
    cou_fk INT,
    FOREIGN KEY (cou_fk) REFERENCES course(id)
);

CREATE TABLE student_module(
    stu_fk INT,
    mod_fk INT,
    PRIMARY KEY (stu_fk, mod_fk),
    FOREIGN KEY (stu_fk) REFERENCES student (id),
    FOREIGN KEY (mod_fk) REFERENCES module (id)
);

CREATE TABLE course_module(
    cou_fk INT,
    mod_fk INT,
    PRIMARY KEY (cou_fk, mod_fk),
    FOREIGN KEY (cou_fk) REFERENCES course (id),
    FOREIGN KEY (mod_fk) REFERENCES module (id)
);
