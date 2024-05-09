--Добавление ролей
INSERT INTO authority (authority) VALUES ('ROLE_STUDENT');
INSERT INTO authority (authority) VALUES ('ROLE_TEACHER');

--Добавление учителя(админа)
INSERT INTO teacher (username, password, name, surname)
VALUES ('teacher', '$2a$12$ijUIpDJ70Cq6P9z5g7m.I.yyz.iNXnPGUnPj1I4RYrf9lhrr/YfR2', 'Учитель', 'Учителевич');

----Добавление связей
INSERT INTO teacher_authorities
VALUES (2, 1)
