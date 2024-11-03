select id, email, first_name, last_name
from developers d
where skill_code & (
    select sum(code) 
    from skillcodes 
    where category = 'Front End'
)
order by id