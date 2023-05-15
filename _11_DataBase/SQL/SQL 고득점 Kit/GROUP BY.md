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


# Level 3

# Level 4
