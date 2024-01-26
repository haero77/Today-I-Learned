# GROUP BY

# Level 2

## 성분으로 구분한 아이스크림 총 주문량

<img src="https://velog.velcdn.com/images/balparang/post/eb271a4b-205f-4c4b-99a1-2c050959fe77/image.png" width="700">

<img src="https://velog.velcdn.com/images/balparang/post/4cfff395-fc09-4c9f-9576-5fd5587b7fcb/image.png" width="700">

```sql
# 상반기 주문 정보 first_half
# flavor(pk) | shipment_id | total_order

# 성분 정보 icecream_info 
# flavor(pk, fk) | ingredient_type 

# 성분 타입과 성분 타입에 대한 총 주문량
# 총 주문량이 작은 순서 대로

select ii.ingredient_type, sum(fh.total_order) as total_order
from first_half fh
    inner join icecream_info ii
    on  fh.flavor = ii.flavor
group by ingredient_type
order by total_order asc;
```

## 진료과별 총 예약 횟수 출력하기


> 출처: https://school.programmers.co.kr/learn/courses/30/lessons/132202

<img src="https://velog.velcdn.com/images/balparang/post/4f683d39-5e56-4e64-86e8-613e513ce45f/image.png" width="800">

```sql
# appointment: 진료 예약 정보

# 2022년 5월에 예약한 환자 수를 '진료과코드' 별로 조회 -> 같은 환자 중복?
# 컬럼명 -> '진료과 코드', '5월 예약 건수'
# 진료과별 예약한 환자 수 기준 오름차순 -> 진료과 코드 기준 오름차순 

select mcdp_cd as 진료과코드, count(*) as 5월예약건수
from appointment
where date_format(apnt_ymd, '%y-%m') like '22-05'
group by mcdp_cd
order by 5월예약건수 asc, 진료과코드 asc; # 따옴표를 쓰면 정렬 불가
```

# Level 3

# Level 4
