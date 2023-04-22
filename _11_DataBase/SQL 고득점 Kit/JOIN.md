> 🛠️ 지속적으로 업데이트 되는 포스팅입니다.

# Level 2

---

# 1. 조건에 맞는 도서와 저자 리스트 출력하기

> 출처: [https://school.programmers.co.kr/learn/courses/30/lessons/144854](https://school.programmers.co.kr/learn/courses/30/lessons/144854)
>

**문제**

Table BOOK

![](https://velog.velcdn.com/images/balparang/post/69512bee-d004-4cdc-9b55-cf1bd1d9d6d8/image.png)

Table AUTHOR

![](https://velog.velcdn.com/images/balparang/post/47e5e450-b902-4cb9-bd68-3d6ce611807a/image.png)


“*'경제' 카테고리에 속하는 도서들의 도서 ID(BOOK_ID), 저자명(AUTHOR_NAME), 출판일(PUBLISHED_DATE) 리스트를 출력하는 SQL문을 작성해주세요. 결과는 출판일을 기준으로 오름차순 정렬해주세요.”*

**풀이**

```sql
SELECT B.BOOK_ID, A.AUTHOR_NAME, DATE_FORMAT(B.PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM BOOK B
    INNER JOIN AUTHOR A
    ON B.AUTHOR_ID = A.AUTHOR_ID
WHERE B.CATEGORY LIKE '경제'
ORDER BY B.PUBLISHED_DATE ASC;
```