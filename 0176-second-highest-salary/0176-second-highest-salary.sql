# Write your MySQL query statement below
Select MAX(salary) AS SecondHighestSalary from Employee Where salary < (Select MAX(salary) from Employee ) 