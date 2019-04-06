SELECT name, population, area
  FROM World
 WHERE area > 3000000 OR population > 25000000;

SELECT MIN(ABS(p1.x - p2.x)) AS shortest
  FROM point AS p1 JOIN point AS p2
    ON p1.x != p2.x;

UPDATE salary
   SET sex = (CASE
                WHEN sex = "m" THEN "f"
                ELSE "m"
              END);

SELECT name
  FROM customer
 WHERE referee_id != 2 OR referee_id IS NULL;

   SELECT customer_number
    FROM orders
GROUP BY customer_number
ORDER BY COUNT(*) DESC
LIMIT 1;

  SELECT *
    FROM cinema
   WHERE MOD(id, 2) = 1 AND description != "boring"
ORDER BY rating DESC;

SELECT *, CASE
            WHEN x + y > z AND x + z > y AND y + z > x THEN "Yes"
            ELSE "No"
          END AS triangle
  FROM triangle;

SELECT *, IF (x + y > z AND x + z > y AND y + z > x, "Yes", "No" ) AS triangle
  FROM triangle;

SELECT e.name, b.bonus
  FROM Employee AS e LEFT JOIN Bonus AS b
    ON e.empId = b.empId
 WHERE bonus < 1000 OR bonus IS NULL;

  SELECT DISTINCT c1.seat_id
    FROM cinema AS c1 JOIN cinema AS c2
      ON ABS(c1.seat_id - c2.seat_id) = 1
   WHERE c1.free = TRUE AND c2.free = TRUE
ORDER BY c1.seat_id;

SELECT s.name
  FROM salesperson AS s
  WHERE s.sales_id NOT IN (SELECT o.sales_id
                             FROM orders AS o LEFT JOIN company AS c
                               ON o.com_id = c.com_id
                            WHERE c.name = "red")