# Optimistic Lock을 활용한 동시성 제어

<img width="1149" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/7f745348-50c2-45f2-930c-4edd22b4f39c">

- T1 의 `SELECT Stock`
- T1 의 업데이트 시에, `version = version + 1` -> 버전이 `2`가 된다.
- T2 가 업데이트할 때, 
  - `WHERE vesion = 1`을 날리는데,
  - 이미 version 이 2라서, 업데이트 실패

### 장점

- 별도의 Lock을 잡지 않으므로, `Pessimistic Lock` 보다 성능상 이점이 있다.

### 단점 

- `UPDATE`가 실패했을 때, 재시도 로직을 개발자가 직접 작성해줘야 한다.
- 충돌이 빈번하게 일어나거나, 빈번하게 일어날 것이라고 예상된다면 `Pessimistic Lock`을 이용하는게 성능상 나을 수 있다.
  - 충돌 빈번할 때 👉 `Pessimistic Lock`
  - 충돌 빈번하지 않을 때 👉 `Optimisitic Lock`

<br>

## 구현
 
### Data JPA Method

```java
public interface StockRepository extends JpaRepository<Stock, Long> {

  @Lock(value = LockModeType.OPTIMISTIC)
  @Query("select s from Stock s where s.id = :id")
  Stock findByIdWithOptimisticLock(@Param(value = "id") Long id);

}
```

- `@Lock(value = LockModeType.OPTIMISTIC)`을 사용

### Facade 만들기 

- Optimistic Lock이 실패했을 경우 재시도를 하기 위함.

```java
@RequiredArgsConstructor
@Service
public class OptimisticLockStockFacade {

  private final OptimisticLockStockService optimisticLockStockService;
  
  public void decrease(Long id, Long quantity) throws InterruptedException {
    while (true) {
      try {
        // 재고 감소 시도 
        optimisticLockStockService.decrease(id, quantity);
        break;
      } catch (Exception e) {
        // 예외 발생 시 재시도    
        Thread.sleep(50);
      }
    }
  }

}
```