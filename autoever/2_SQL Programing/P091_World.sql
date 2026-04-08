--1번
SELECT * FROM city WHERE Population < 1000 AND Name LIKE 'A%';
--2번
SELECT Name, CountryCode FROM city WHERE CountryCode = 'JPN' AND Population >= 1000000 AND Population <= 2000000;
SELECT Name, CountryCode FROM city WHERE CountryCode = 'JPN' AND Population BETWEEN 1000000 AND 2000000;
--3번
SELECT * FROM city WHERE Name LIKE '%kim%';
--4번
SELECT COUNT(*) FROM city;
SELECT (SELECT COUNT(*) FROM city) + (SELECT COUNT(*) FROM country) + (SELECT COUNT(*) FROM countrylanguage) AS total_records_count;
--5번
SELECT CountryCode, Population FROM city ORDER BY Population ASC LIMIT 5;
--6번
SELECT CountryCode, Population FROM city ORDER BY Population DESC LIMIT 5;