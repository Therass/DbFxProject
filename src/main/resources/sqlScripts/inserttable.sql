INSERT INTO main_table (fruit, sort, amount, provider)
SELECT * FROM (SELECT 'apple' AS fruit, 'green' AS sort, 1 AS amount, 'India' AS provider) AS tmp
WHERE NOT EXISTS (
    SELECT fruit FROM main_table WHERE fruit = 'apple' AND sort = 'green' AND provider = 'India'
) LIMIT 1;

INSERT INTO main_table (fruit, sort, amount, provider)
SELECT * FROM (SELECT 'apple' AS fruit, 'red' AS sort, 1 AS amount, 'Ukraine' AS provider) AS tmp
WHERE NOT EXISTS (
    SELECT fruit,sort,provider FROM main_table WHERE fruit = 'apple' AND sort = 'red' AND provider = 'Ukraine'
) LIMIT 1;

INSERT INTO main_table (fruit, sort, amount, provider)
SELECT * FROM (SELECT 'orange' AS fruit, 'simple' AS sort, 1 AS amount, 'China' AS provider) AS tmp
WHERE NOT EXISTS (
    SELECT fruit,sort,provider FROM main_table WHERE fruit = 'orange' AND sort = 'simple' AND provider = 'China'
) LIMIT 1;

