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

CREATE TABLE exam_results
(
    exam_id      SERIAL,
    subject_name text,
    score        int,
    student_id   integer REFERENCES students (id)
);

insert into exam_results (subject_name, score, student_id)
VALUES ('Math', 3, 1);
insert into exam_results (subject_name, score, student_id)
VALUES ('History', 2, 2);
insert into exam_results (subject_name, score, student_id)
VALUES ('Math', 1, 3);
insert into exam_results (subject_name, score, student_id)
VALUES ('Math', 4, 4);


--Try different kind of indexes (B-tree, Hash, GIN, GIST) for your fields.
insert into students_subject (student_id, subject_id) values  (1,1);
insert into students_subject (student_id, subject_id) values  (1,2);
insert into students_subject (student_id, subject_id) values  (2,2);
--Analyze performance for each of the indexes (use ANALYZE and EXPLAIN). Check the size of the index.

--Try to set index before inserting test data and after. What was the time? Test data:
  --a. 100K of users

INSERT INTO students(name, surname, phonenumbers, primaryskill)
SELECT md5(random()::text), md5(random()::text), md5(random()::text), md5(random()::text)
FROM generate_series(1,100000) id;
  --b. 1K of subjects

INSERT INTO subjects(subjectname, tutor)
SELECT md5(random()::text), md5(random()::text)
FROM generate_series(1,1000) id;
  --c. 1 million of marks
  INSERT INTO exam_results(subject_name, score)
SELECT md5(random()::text), floor(random() * 5 + 1)::int
FROM generate_series(1,1000000) id;
  --Test queries:
  --a. Find user by name (exact match)
select * from students where students.name LIKE 'Bil%';
  --b. Find user by surname (partial match)
select * from students where students.surname LIKE 'Cl%';
  --c. Find user by phone number (partial match)
select * from students where students.phonenumbers LIKE '%921%';
  --d. Find user with marks by user surname (partial match)
SELECT score, student_id FROM exam_results
LEFT JOIN students s on exam_results.student_id = s.id
WHERE exam_results.score > 4;
  --Add your investigations to separate document. (1 point)

create index names_students on students USING btree (id);
create index id_subjects on subjects USING hash (id);
create index id_students on students_subject (id);

--  5. Add trigger that will update column updated_datetime to current date in case of updating any of student. (0.3 point)

CREATE FUNCTION update_student() RETURNS TRIGGER AS $$
BEGIN
    UPDATE students SET UpdatedDateTime =now() WHERE PrimarySkill = NEW.PrimarySkill;
    RETURN NEW;
END; $$
LANGUAGE plpgsql;CREATE TRIGGER update_student AFTER INSERT ON students FOR ROW EXECUTE PROCEDURE update_student();

-- 6. Add validation on DB level that will check username on special characters
--(reject student name with next characters '@', '#', '$'). (0.3 point)

alter table students add constraint check_name
check(Name ~ * '^[^@#$]+$');

--7. Create snapshot that will contain next data: student name, student surname, subject name, mark
--(snapshot means that in case of changing some data in source table – your snapshot should not change). (0.3 point)
--todo
--8. Create function that will return average mark for input user. (0.3 point)
--9. Create function that will return avarage mark for input subject name. (0.3 point).
--todo
--10. Create function that will return student at "red zone" (red zone means at least 2 marks <=3). (0.3 point)
create or replace function redZone21()
returns table(name text)
AS $$ BEGIN RETURN QUERY SELECT s.name FROM students s INNER JOIN exam_results er on s.id = er.student_id;END;$$
LANGUAGE plpgsql;



