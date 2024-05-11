----Добавление ролей
INSERT INTO authority (authority) VALUES ('ROLE_STUDENT');
INSERT INTO authority (authority) VALUES ('ROLE_TEACHER');

----Добавление учителя(админа)
INSERT INTO users (username, password)
VALUES ('teacher', '$2a$12$ijUIpDJ70Cq6P9z5g7m.I.yyz.iNXnPGUnPj1I4RYrf9lhrr/YfR2');
INSERT INTO teacher (id, name, surname)
VALUES (1, 'Учитель', 'Учителевич');

------Добавление связей
INSERT INTO USERS_AUTHORITIES (authorities_id, user_id)
VALUES (1, 1);
SELECT * FROM INFORMATION_SCHEMA.TABLES