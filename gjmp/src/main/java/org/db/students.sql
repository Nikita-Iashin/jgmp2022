-- 1. Design database for CDP program. Your DB should store information about students (name, surname, date of birth, phone
-- numbers, primary skill, created_datetime, updated_datetime etc.), subjects (subject name, tutor, etc.)
-- and exam results (student, subject, mark).
-- 2. Please add appropriate constraints (primary keys, foreign keys, indexes, etc.).
-- 3. Design such kind of database for PostrgeSQL. Show your design in some suitable way (PDF, PNG, etc). (1 point)

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    Name VARCHAR(255),
    Surname VARCHAR(255),
    DateOfBirth DATE,
    PhoneNumbers VARCHAR(255),
    PrimarySkill VARCHAR(255),
    CreatedDateTime TIMESTAMP,
    UpdatedDateTime TIMESTAMP
);

insert into students (Name, Surname, DateOfBirth, phonenumbers, PrimarySkill, CreatedDateTime, UpdatedDateTime)
values ('Bill','Clinton',DATE '11/11/1967','+7(921)111-11-11','Politics',current_timestamp,current_timestamp);

insert into students (Name, Surname, DateOfBirth, phonenumbers, PrimarySkill, CreatedDateTime, UpdatedDateTime)
values ('Joe','Doe',DATE '12/11/2000','+7(812)221-11-11','Math',current_timestamp,current_timestamp);

insert into students (Name, Surname, DateOfBirth, phonenumbers, PrimarySkill, CreatedDateTime, UpdatedDateTime)
values ('Ann','Zoo',DATE '11/11/1967','911','History',current_timestamp,current_timestamp);

insert into students (Name, Surname, DateOfBirth, phonenumbers, PrimarySkill, CreatedDateTime, UpdatedDateTime)
values ('Thomas','Loo',DATE '01/08/2005','555-555-05','Astronomy',current_timestamp,current_timestamp);

select * from students;
drop table students;

CREATE TABLE subjects (
    id SERIAL PRIMARY KEY ,
    SubjectName VARCHAR(255),
    Tutor VARCHAR(255)
);

insert into subjects (SubjectName, Tutor) VALUES ('Math', 'Ilia Ivanov');
insert into subjects (SubjectName, Tutor) VALUES ('Math', 'Lora Gray');

select * from subjects;

CREATE TABLE students_subject
(
    id        BIGSERIAL PRIMARY KEY,
    student_id INTEGER NOT NULL REFERENCES students,
    subject_id   INTEGER NOT NULL REFERENCES subjects,
    UNIQUE (student_id, subject_id)
);

insert into students_subject (student_id, subject_id) values  (1,1);
insert into students_subject (student_id, subject_id) values  (1,2);
insert into students_subject (student_id, subject_id) values  (2,2);

create index names_students on students USING btree (id);
create index id_subjects on subjects USING hash (id);
create index id_students on students_subject (id);

CREATE FUNCTION update_student() RETURNS TRIGGER AS $$
BEGIN
    UPDATE students SET UpdatedDateTime =now() WHERE PrimarySkill = NEW.PrimarySkill;
    RETURN NEW;
END; $$
LANGUAGE plpgsql;

CREATE TRIGGER update_student
    AFTER INSERT ON students
    FOR ROW
    EXECUTE PROCEDURE update_student();

alter table students
    add constraint check_name
    check (Name ~* '^[^@#$]+$');



