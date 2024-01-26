# Pessimistic Lock을 활용한 동시성 제어

> `Pessimitic Lock`을 활용해서 `Race Condition`을 해결해보자.

## Pessimistic Lock 

- 실제로 DB에 락을 걸어서 정합성을 해결하는 방법
- `Exclusive Lock(배타락)`을 걸게 되면, 다른 트랜잭션에서 락이 해제되기 전에 락을 걸거나 데이터를 가져갈 수 없게 된다.

### 장점 

- 충돌이 빈번하게 일어난다면, `Optimistic Lock` 보다 성능이 좋을 수 있다.
- Lock을 통해 UPDATE를 제어하기 때문에, 데이터 정합성이 어느정도(?) 보장된다.

### 단점

- 별도의 Lock을 잡기 때문에, 성능 감소가 있을 수 있다.

## 구현 방법

### Data JPA 에서의 사용법

### SQL 문에서 확인

```sql
select
    stock0_.id as id1_0_,
    stock0_.product_id as product_2_0_,
    stock0_.quantity as quantity3_0_ 
from
    stock stock0_ 
where
    stock0_.id=? for update # for update 부분이 락을 거는 부분
```

- WHERE 이후의 `for update` 부분이 락을 걸고, 데이터를 가져오는 부분이다.