CREATE TABLE IF NOT EXISTS faculty(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS school(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    fac_fk INT,
    CONSTRAINT FOREIGN KEY school_fk_fac (fac_fk) REFERENCES faculty(id)
);

CREATE TABLE IF NOT EXISTS lecturer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS module(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    credit SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS course(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    credit SMALLINT NOT NULL,
    sch_fk INT,
    lec_fk INT,
    CONSTRAINT FOREIGN KEY course_fk_sch (sch_fk) REFERENCES school(id),
    CONSTRAINT FOREIGN KEY course_fk_lec (lec_fk) REFERENCES lecturer(id)
);

CREATE TABLE IF NOT EXISTS lecturer_module(
    lec_fk INT,
    mod_fk INT,
    PRIMARY KEY (lec_fk, mod_fk),
    CONSTRAINT FOREIGN KEY lecmod_fk_lec (lec_fk) REFERENCES lecturer (id),
    CONSTRAINT FOREIGN KEY lecmod_fk_mod (mod_fk) REFERENCES module (id)
);

CREATE TABLE IF NOT EXISTS student(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    reg_date DATE NOT NULL,
    cou_fk INT,
    CONSTRAINT FOREIGN KEY student_fk_cou (cou_fk) REFERENCES course(id)
);

CREATE TABLE IF NOT EXISTS student_module(
    stu_fk INT,
    mod_fk INT,
    PRIMARY KEY (stu_fk, mod_fk),
    CONSTRAINT FOREIGN KEY stumod_fk_stu (stu_fk) REFERENCES student (id),
    CONSTRAINT FOREIGN KEY stumod_fk_mod (mod_fk) REFERENCES module (id)
);

CREATE TABLE IF NOT EXISTS course_module(
    cou_fk INT,
    mod_fk INT,
    PRIMARY KEY (cou_fk, mod_fk),
    CONSTRAINT FOREIGN KEY coumod_fk_cou (cou_fk) REFERENCES course (id),
    CONSTRAINT FOREIGN KEY modmod_fk_mod (mod_fk) REFERENCES module (id)
);
