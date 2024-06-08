----Добавление ролей
INSERT INTO authority (authority)
SELECT 'ROLE_STUDENT'
WHERE NOT EXISTS (SELECT 1 FROM authority WHERE authority='ROLE_STUDENT');

INSERT INTO authority (authority)
SELECT 'ROLE_TEACHER'
WHERE NOT EXISTS (SELECT 1 FROM authority WHERE authority='ROLE_TEACHER');

----Добавление учителя(админа)
INSERT INTO users (username, password)
SELECT 'teacher', '$2a$12$ijUIpDJ70Cq6P9z5g7m.I.yyz.iNXnPGUnPj1I4RYrf9lhrr/YfR2'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='teacher');
INSERT INTO teacher (id, name, surname)
SELECT 1, 'Учитель', 'Учителевич'
WHERE NOT EXISTS (SELECT 1 FROM teacher WHERE id=1);

------Добавление связей
INSERT INTO USERS_AUTHORITIES (authorities_id, user_id)
SELECT 1, 1
WHERE NOT EXISTS (SELECT 1 FROM USERS_AUTHORITIES WHERE authorities_id=1);
--SELECT * FROM INFORMATION_SCHEMA.TABLES