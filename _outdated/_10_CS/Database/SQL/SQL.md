# SQL

데이터를 다루기 위한 관계형 데이터베이스 언어로, 기능에 따라 DDL, DML, DCL로 나뉜다.
- DDL: 
  - 테이블의 구조나 관계 정의 
  - CREATE, ALTER, DROP
- DML:
  - 테이블에 데이터 `검색, 삽입, 수정, 삭제` 할 때 사용
  - SELECT, INSERT, UPDATE, DELETE
- DCL:
  - 데이터의 사용권한을 관리 
  - GRANT, REVOKE

<br>

# 데이터 조작어 - 검색

## 1. SELECT 

### 집합

> _Q. 출판사가 '굿스포츠' 혹은 '대한미디어'인 도서를 검색하시오._
> ```sql
> select *
> from book
> where publisher in('굿스포츠', '대한미디어');
> ``` 


- `IN` 연산자: 집합의 원소인지 판단

<br>

### 패턴

- LIKE 연산자 사용

- 문자열 검색 시 LIKE와 같이 사용하는 와일드 문자

| 와일드 문자 | 의미                | 사용 예                              |
|--------|-------------------|-----------------------------------|
| +      | 문자열을 연결           | '골프' + '바이블' : '골프 바이블'           |
| %      | 0개 이상의 문자열과 일치    | '%축구%': 축구를 포함하는 문자열              |
| []     | 1개의 문자와 일치        | '[0-5]%': 0-5 사이 숫자로 시작하는 문자열     |
| [^]    | 1개의 문자와 불일치       | '[^0-5]%': 0-5 사이 숫자로 시작하지 않는 문자열 |
| _      | 특정 위치의 1개의 문자와 일치 | '_구%': 두 번째 위치에 '구'가 들어가는 문자열     |

<br>

## 2. 집계함수와 GROUP BY

### GROUP BY와 HAVING절의 문법과 주의사항

**_GROUP BY로 투플을 그룹으로 묶은 후 SELECT 절에는 GROUP BY에서 사용한 속성과 집계함수만 나올 수 있다._**

맞는예 
```sql
select custid, sum(salesprice)
from orders
group by custid;
```

틀린 예
```sql
select bookid, sum(salesprice) /* select 절에 bookid 속성이 올 수 없다 */
from orders
group by custid;
```

## 3. 서브쿼리

> 출판사별로 출판사의 평균 도서 가격보다 비싼 도서를 구하시오
> ```sql
> select b1.bookname
> from book b1
> where b1.price > (select avg(b2.price)
>                   from book b2
>                   where b2.publisher = b1.publisher);
> ```