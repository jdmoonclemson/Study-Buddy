-- Students
INSERT INTO STUDENT (id, name, email) VALUES (1, 'Ava Carter', 'ava@clemson.edu');
INSERT INTO STUDENT (id, name, email) VALUES (2, 'Noah Patel', 'noah@clemson.edu');
INSERT INTO STUDENT (id, name, email) VALUES (3, 'Liam Chen', 'liam@clemson.edu');


-- Courses (ElementCollection table)
INSERT INTO student_courses (student_id, course_code) VALUES (1, 'CPSC-2150');
INSERT INTO student_courses (student_id, course_code) VALUES (1, 'MATH-2060');
INSERT INTO student_courses (student_id, course_code) VALUES (2, 'CPSC-2150');
INSERT INTO student_courses (student_id, course_code) VALUES (3, 'CPSC-2150');


-- Availability
INSERT INTO AVAILABILITY (student_id, day_of_week, start_time, end_time)
VALUES (1, 'MONDAY', '14:00:00', '16:00:00');
INSERT INTO AVAILABILITY (student_id, day_of_week, start_time, end_time)
VALUES (1, 'WEDNESDAY', '10:00:00', '12:00:00');
INSERT INTO AVAILABILITY (student_id, day_of_week, start_time, end_time)
VALUES (2, 'MONDAY', '15:00:00', '17:00:00');
INSERT INTO AVAILABILITY (student_id, day_of_week, start_time, end_time)
VALUES (3, 'WEDNESDAY', '11:00:00', '13:00:00');