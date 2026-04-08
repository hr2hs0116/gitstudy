-- 1번
SELECT * FROM members WHERE member_id = 2 OR member_id = 4;
SELECT * FROM members WHERE member_id IN (2, 4);

-- 2번
SELECT member_id, age FROM members WHERE member_id >= 1 AND member_id <= 3;
SELECT member_id, age  FROM members WHERE member_id BETWEEN 1 AND 3;

--3번
SELECT * FROM members where age >= 30 and age <= 39;
SELECT * FROM members WHERE age BETWEEN 30 AND 39;

--4번
SELECT name FROM members WHERE member_id % 2 = 1 and age % 2 = 0;
SELECT name FROM members  WHERE MOD(member_id, 2) = 1 AND MOD(age, 2) = 0;
SELECT name FROM members WHERE (member_id & 1) = 1 AND (age & 1) = 0;
SELECT name FROM members WHERE member_id % 2 != 0 AND age % 2 = 0;

--5번
SELECT member_id as "번호", name as "성함", age as "나이" FROM members ORDER BY age ASC;