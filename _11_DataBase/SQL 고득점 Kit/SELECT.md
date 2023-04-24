


> 🛠️ 지속적으로 업데이트 되는 포스팅입니다.

# Level 1

---

## 1. 모든 레코드 조회하기

> 문제 출처 : https://school.programmers.co.kr/learn/courses/30/lessons/59034
>

**문제**

_"동물 보호소에 들어온 모든 동물의 정보를 ANIMAL_ID순으로 조회하는 SQL문을 작성해주세요. SQL을 실행하면 다음과 같이 출력되어야 합니다."_

![https://velog.velcdn.com/images/balparang/post/f0371976-3317-4d4a-a3b7-d72f239a2abb/image.png](https://velog.velcdn.com/images/balparang/post/f0371976-3317-4d4a-a3b7-d72f239a2abb/image.png)

**쿼리**

```sql
SELECT *
FROM ANIMAL_INS
ORDER BY ANIMAL_ID ASC;
```

**풀이**

본 문제는 ‘정렬 검색’에 대해 묻고 있다.

1. ‘모든’ 속성을 조회하므로 SELECT절에 ‘*(애스터리스크)’ 를 작성한다.
2. 결과 릴레이션이 `ANIMAL_ID` 속성 기준 오름차순 정렬되어야하므로, ORDER BY 절에 정렬 기준이 되는 속성을 지정하고 `ASC` 를 이용하여 오름차순 정렬되게 한다.

---

## 2. 역순 정렬하기

> 출처 :  https://school.programmers.co.kr/learn/courses/30/lessons/59035
>

**문제**

_"동물 보호소에 들어온 모든 동물의 이름과 보호 시작일을 조회하는 SQL문을 작성해주세요. 이때 결과는 ANIMAL_ID 역순으로 보여주세요. SQL을 실행하면 다음과 같이 출력되어야 합니다."_

**쿼리**

```sql
SELECT NAME, DATETIME
FROM ANIMAL_INS
ORDER BY ANIMAL_ID DESC;
```

**풀이**

1. 이름 속성(DATE) 과 , 보호 시작일 속성(DATETIME) 을 조회 하므로, 해당 두 속성을 `SELECT` 절에 작성한다.
2. 결과 테이블에서 ANIMAL_ID 속성의 역순으로 나타내야하므로, `ORDER BY` 절에 해당 속성과 `DESC` 키워드를 같이 작성한다. (오름차순의 경우 `ASC`)

---

## 3. 아픈 동물 찾기

> 출처 :  https://school.programmers.co.kr/learn/courses/30/lessons/59036
>

**문제**

_"동물 보호소에 들어온 동물 중 아픈 동물 (`INTAKE_CONDITION`이 Sick 인 경우) 의 아이디와 이름을 조회하는 SQL 문을 작성해주세요. 이때 결과는 아이디 순으로 조회해주세요."_

**쿼리**

```sql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION = 'Sick'
ORDER BY ANIMAL_ID ASC;
```

**풀이**

1. `SELECT`절을 이용해 아이디(ANIMAL_ID)와 이름(NAME) 속성을 검색한다.
2. 검색할 때 ‘아픈 동물’ 을 만족하는 튜플을 검색해야하므로, 조건 검색 키워드 `WHERE` 를 이용한다. `WHERE` 절에는 ‘아픈 동물’ 을 의미하는 `INTAKE_CONDITION = ‘Sick’` 을 작성한다.
3. 결과 테이블이 아이디순으로 조회되어야 하므로 `ORDER BY` 절에 아이디 속성과 `ASC` 키워드를 작성한다.

---

## 4. 조건에 맞는 회원 수 구하기 (DATE 조건 검색)

> 출처: [https://school.programmers.co.kr/learn/courses/30/lessons/131535](https://school.programmers.co.kr/learn/courses/30/lessons/131535)
>

**문제**

![](https://velog.velcdn.com/images/balparang/post/fd7123bb-cf6c-4c5e-b303-78579c3e4201/image.png)

_"`USER_INFO`  테이블에서 2021년에 가입한 회원 중 나이가 20세 이상 29세 이하인 회원이 몇 명인지 출력하는 SQL문을 작성해주세요."_

**풀이 1 - BETWEEN  활용**

```sql
SELECT COUNT(*) AS USERS
FROM USER_INFO UI
WHERE UI.JOINED BETWEEN '2021-01-01' AND '2021-12-31'
	AND UI.AGE BETWEEN 20 AND 29;
```

**풀이 2 - YEAR 함수 활용**

```sql
// 출처: https://school.programmers.co.kr/questions/41671
SELECT COUNT(*) AS USERS
FROM USER_INFO
WHERE YEAR(JOINED) = 2021
	AND AGE BETWEEN 20 AND 29;
```

**풀이 3 - LIKE 활용**

```sql
// 출처: https://school.programmers.co.kr/questions/42856
SELECT COUNT(USER_ID)
FROM USER_INFO
WHERE JOINED LIKE '2021%'
	AND AGE BETWEEN 20 AND 29
```

**풀이 4 - DATE_FORMAT 함수 활용**

```sql
// 출처: https://school.programmers.co.kr/questions/41658
SELECT COUNT(AGE)
FROM USER_INFO
WHERE DATE_FORMAT(JOINED, '%Y') = 2021
	AND AGE BETWEEN 20 AND 29
```

**풀이 5 - LEFT 함수 활용**

```sql
// 출처: https://school.programmers.co.kr/questions/40586
SELECT COUNT(USER_ID) AS USERS
FROM USER_INFO
WHERE LEFT(JOINED, 4) = "2021" 
	AND AGE >= 20 AND AGE <= 29;
```

## 5. 과일로 만든 아이스크림 고르기

> 출처: https://school.programmers.co.kr/learn/courses/30/lessons/133025

**풀이**

```sql
select fh.flavor
from first_half fh
         left outer join icecream_info info
         on fh.flavor = info.flavor
where
    fh.total_order > 3000
    and info.ingredient_type like 'fruit_based'
order by fh.total_order desc;
```

## 6.12세 이하인 여자 환자 목록 출력하기

> 출처: https://school.programmers.co.kr/learn/courses/30/lessons/132201

![](https://velog.velcdn.com/images/balparang/post/2028e805-6e6e-4d1a-b416-e9b6513e5308/image.png)

**풀이 - CASE WHEN 활용**

```sql
# 환자 정보 patient 테이블
# 12세 이하 & gend_cd가 여자 
# 전화번호가 없는 경우 none 출력
# 나이 기준 내림차순, 나이 같다면 환자이름 오름차순
select 
    pt_name, 
    pt_no, 
    gend_cd, 
    age, 
    case 
        when tlno is null then 'NONE'
        else tlno
        end as tlno
from patient
where 
    gend_cd like 'W'   
    and age <= 12
order by age desc, pt_name asc;
```

