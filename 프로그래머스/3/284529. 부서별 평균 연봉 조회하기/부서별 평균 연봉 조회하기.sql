-- 코드를 작성해주세요

SELECT HD.DEPT_ID, HD.DEPT_NAME_EN, ROUND(AVG(HE.SAL), 0) AS AVG_SAL
FROM HR_DEPARTMENT AS HD
JOIN HR_EMPLOYEES AS HE ON HD.DEPT_ID = HE.DEPT_ID
GROUP BY HD.DEPT_ID
ORDER BY AVG_SAL DESC;
