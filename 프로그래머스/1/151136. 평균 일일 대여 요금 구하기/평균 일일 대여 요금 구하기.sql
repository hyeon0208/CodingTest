-- 코드를 입력하세요
select ROUND(avg(daily_fee), 0) as AVERAGE_FEE 
from CAR_RENTAL_COMPANY_CAR 
group by CAR_TYPE
having car_type = 'SUV';