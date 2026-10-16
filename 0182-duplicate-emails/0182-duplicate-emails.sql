# Write your MySQL query statement below
select email As Email from Person group by email having COUNT(*) > 1