-- 6번
SELECT name, age FROM members ORDER BY name ASC;
SELECT name,age FROM members ORDER BY 1;
--이름이 같은경우, 나이 오름차순
SELECT name,age FROM members ORDER BY name ASC, age ASC;

--7번
UPDATE members SET age = 33 WHERE member_id = 1 OR member_id = 3;
UPDATE members SET age = 33 WHERE member_id IN (1, 3);

--8번
DELETE FROM members WHERE member_id = 5;

--9번
INSERT INTO members (name, age) VALUES ('정선', 32);
INSERT INTO members VALUES (NULL, '정선', 32);

--10번
SELECT name, age FROM members ORDER BY age DESC LIMIT 1;
SELECT name, age FROM members WHERE age = (SELECT MAX(age) FROM members);