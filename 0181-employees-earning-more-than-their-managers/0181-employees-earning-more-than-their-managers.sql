# Write your MySQL query statement below
Select e.name AS Employee from Employee e INNER JOIN Employee m ON e.managerId = m.id Where e.salary > m.salary