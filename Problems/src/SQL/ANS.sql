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

SELECT Email
FROM Person
GROUP BY Email
HAVING COUNT(*) > 1;

SELECT p.FirstName, p.LastName, a.City, a.State
FROM Person AS p LEFT JOIN Address AS a
ON p.PersonId = a.PersonId;

SELECT e1.name AS Employee
FROM Employee e1 LEFT JOIN Employee e2
ON e1.ManagerId = e2.Id
WHERE e1.Salary > e2.Salary;

SELECT name AS Customers
FROM Customers
WHERE Id NOT IN (SELECT CustomerId FROM Orders);

SELECT ROUND(IFNULL((SELECT COUNT(*)
                    FROM (SELECT DISTINCT requester_id, accepter_id FROM request_accepted) AS a)
                    /
                    (SELECT COUNT(*)
                    FROM (SELECT DISTINCT sender_id, send_to_id FROM friend_request) AS r), 0), 2) AS accept_rate;

SELECT MAX(num) AS num
FROM (SELECT num
      FROM my_numbers
      GROUP BY num
      HAVING COUNT(num) = 1) AS a;

SELECT class
FROM (SELECT DISTINCT student, class FROM courses) AS c
GROUP BY class
HAVING COUNT(*) >= 5;

SELECT class
FROM courses
GROUP BY class
HAVING COUNT(DISTINCT student) >= 5;

SELECT w1.Id
FROM Weather AS w1 LEFT JOIN Weather AS w2
ON DATEDIFF(w1.RecordDate, w2.RecordDate) = 1
WHERE w1.Temperature > w2.Temperature;

DELETE p1
FROM Person p1, Person p2
WHERE p1.Email = p2.Email AND p1.Id > p2.Id;

SELECT IFNULL((SELECT DISTINCT Salary
FROM Employee
ORDER BY Salary DESC
LIMIT 1
OFFSET 1), NULL) AS SecondHighestSalary;

SELECT Name
FROM Employee
WHERE Id IN (SELECT ManagerId FROM Employee GROUP BY ManagerId HAVING COUNT(*) >= 5);

SELECT id, (CASE WHEN p_id IS NULL THEN "Root"
                 WHEN id NOT IN (SELECT p_id FROM tree WHERE p_id IS NOT NULL) THEN "Leaf"
                 ELSE "Inner"
            END) AS Type
FROM tree

SELECT IF(id < (SELECT COUNT(*) FROM seat), IF(MOD(id, 2) = 0, id - 1, id + 1), IF(MOD(id, 2) = 0, id - 1, id)) AS id, student
FROM seat
ORDER BY id;

SELECT ROUND(MIN(SQRT(POW(p1.x - p2.x, 2) + POW(p1.y - p2.y, 2)))
             , 2) AS shortest
FROM point_2d AS p1 JOIN point_2d AS P2
ON p1.x != p2.x OR p1.y != p2.y;

SELECT SUM(TIV_2016) AS TIV_2016
FROM insurance
WHERE TIV_2015 IN (SELECT TIV_2015 FROM insurance GROUP BY TIV_2015 HAVING COUNT(*) > 1)
      AND
      CONCAT(LAT, LON) IN (SELECT CONCAT(LAT, LON) FROM insurance GROUP BY LAT, LON HAVING COUNT(*) = 1)
;

SELECT id, COUNT(*) AS num
FROM (
    SELECT requester_id AS id FROM request_accepted
    UNION ALL
    SELECT accepter_id AS id FROM request_accepted
    ) AS t1
GROUP BY id
ORDER BY COUNT(*) DESC
LIMIT 1;

SELECT dept_name, IFNULL(student_number, 0) AS student_number
FROM department AS d LEFT JOIN (
                                SELECT dept_id, COUNT(*) AS student_number
                                FROM student
                                GROUP BY dept_id
                                ) AS s
ON d.dept_id = s.dept_id
ORDER BY student_number DESC, dept_name ASC;

SELECT dept_name, COUNT(student_id) AS student_number
FROM department AS d LEFT JOIN student AS s
ON d.dept_id = s.dept_id
GROUP BY dept_name
ORDER BY student_number DESC, dept_name;

SELECT Name
FROM Candidate
WHERE id = (SELECT CandidateId FROM Vote GROUP BY CandidateId ORDER BY COUNT(*) DESC LIMIT 1);

SELECT question_id AS survey_log
FROM survey_log
GROUP BY question_id
ORDER BY COUNT(answer_id) / COUNT(IF(action = "show", 1, 0)) DESC
LIMIT 1;

SELECT s2.Score, (SELECT COUNT(DISTINCT Score) FROM Scores AS s1 WHERE s1.Score >= s2.Score) AS Rank
FROM Scores AS s2
ORDER BY Score DESC;

SELECT DISTINCT num1 AS ConsecutiveNums
FROM (SELECT l1.Id, l1.Num AS num1, l2.Num AS num2 FROM Logs AS l1 LEFT JOIN Logs AS l2 ON l1.Id = l2.Id - 1) AS l12 LEFT JOIN Logs AS l3 ON l12.Id = l3.Id - 2
WHERE num1 = num2 AND num2 = Num;

SELECT DISTINCT l1.Num AS ConsecutiveNums
FROM Logs AS l1, Logs AS l2, Logs AS l3
WHERE l1.Id = l2.Id - 1 AND l2.Id = l3.Id -1 AND l1.Num = L2.Num AND L2.Num = L3.Num;

SELECT D.Name AS Department, E.Name AS Employee, E.Salary
FROM Department D LEFT JOIN Employee E
ON D.Id = E.DepartmentId
WHERE (D.Id, E.Salary) IN (
                            SELECT DepartmentId, MAX(Salary) AS ms
                            FROM Employee
                            GROUP BY DepartmentId
                          );

# SELECT followee AS follower, COUNT(DISTINCT follower) AS num
# FROM follow
# WHERE followee in (SELECT follower FROM follow)
# GROUP BY followee
# ORDER BY followee

select f1.follower, count(distinct f2.follower) as num
from follow f1
join follow f2 on f1.follower = f2.followee
group by f1.follower
order by f1.follower;


SELECT g1.pay_month, g1.department_id, (CASE WHEN g1.avg_amount > g2.whole_avg_amount THEN "higher"
                                            WHEN g1.avg_amount < g2.whole_avg_amount THEN "lower"
                                            ELSE "same"
                                       END) AS comparison
FROM
(SELECT date_format(pay_date, '%Y-%m') AS pay_month, department_id, AVG(amount) AS avg_amount
FROM salary AS s LEFT JOIN employee AS e
ON s.employee_id = e.employee_id
GROUP BY department_id, pay_month) AS g1,
(SELECT date_format(pay_date, '%Y-%m') AS pay_month, AVG(amount) AS whole_avg_amount FROM salary GROUP BY pay_month) AS g2
WHERE g1.pay_month = g2.pay_month
ORDER BY department_id, pay_month;

SELECT DISTINCT s1.*
FROM stadium AS s1,
     stadium AS s2,
     stadium AS s3
WHERE s1.people >= 100 AND s2.people >= 100 AND s3.people >= 100
      AND
      ((s1.id = s2.id - 1 AND s2.id = s3.id - 1) OR
       (s2.id = s3.id - 1 AND s3.id = s1.id - 1) OR
       (s3.id = s1.id - 1 AND s1.id = s2.id - 1))
ORDER BY s1.id;

SELECT Request_at AS Day, ROUND((COUNT(IF(Status != "completed", 1, NULL)) / COUNT(*)), 2) AS "Cancellation Rate" FROM Trips WHERE Request_at BETWEEN "2013-10-01" AND "2013-10-03" AND Client_Id NOT IN (SELECT Users_Id FROM Users WHERE Banned = "Yes") GROUP BY Request_at ORDER BY Request_at



