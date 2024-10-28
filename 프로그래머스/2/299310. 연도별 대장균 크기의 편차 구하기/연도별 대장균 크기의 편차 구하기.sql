with MAX_SIZE_OF_YEAR as (
    select year(DIFFERENTIATION_DATE) as YEAR, max(SIZE_OF_COLONY) as LENGTH
    from ECOLI_DATA
    group by year(DIFFERENTIATION_DATE)
)

select moy.YEAR, (moy.LENGTH - ed.SIZE_OF_COLONY) as YEAR_DEV, ed.ID from ECOLI_DATA ed
JOIN MAX_SIZE_OF_YEAR moy ON year(ed.DIFFERENTIATION_DATE) = moy.YEAR
order by YEAR asc, YEAR_DEV asc;