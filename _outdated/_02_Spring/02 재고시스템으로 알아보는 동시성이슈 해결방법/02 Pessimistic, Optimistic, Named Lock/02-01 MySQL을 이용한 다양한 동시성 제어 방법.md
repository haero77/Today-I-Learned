# MySQL을 이용한 다양한 동시성 제어 방법

## 1. Pessimistic Lock

- 실제로 데이터에 Lock을 걸어 정합성을 맞추는 방법
- `exclusive lock`을 걸게 되며, 다른 트랜잭션에서는 lock이 해제되기 전까지 데이터를 가져갈 수 없게된다.
- `데드락`이 걸릴 수 있으므로 주의해서 사용해야함

<br>

### 원리 

<img width="633" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/6a66440d-5af5-4694-a41d-9928b52243ab">

- `Server1`에서 Lock 획득
  - 데이터에는 Lock을 획득한 스레드만 접근 가능 
- `Server1`이 Lock을 해제하기 전까지 다른 서버에서 데이터에 접근 불가능 

<br>

## 2. Optimistic Lock

- 실제로 Lock을 이용하지 않고 버전을 이용함으로써 정합성을 맞추는 방법
- 먼저 데이터를 읽은 후에 update를 수행할 때 현재 내가 읽은 버전이 맞는지 확인하며 업데이트한다.
- 내가 읽은 버전에서 수정사항이 생겼을 경우는 application에서 다시 읽은 후에 작업을 수행해야 한다.

### 원리

<img width="823" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/d346844e-49cf-40e1-a33b-6a2c1b5d32e7">

- `Server1`이 version이 `1`인 데이터(row)를 읽어온다.
- UPDATE 쿼리 실행 👉 데이터 갱신 & version을 `2`로 업데이트.
  - `WHERE` 조건에 version 명시

<img width="785" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/70d279ac-46be-48ff-8854-844bb4f33427">

- `Server2`가 version이 `1`인 데이터(row)를 읽어온다.
- UPDATE 쿼리 실행 👉 **명시된 version이 다르므로 데이터는 갱신되지 않음**
- 애플리케이션에서 version `2`인 데이터를 읽고 다시 UPDATE


<br>

## 3. Named Lock

- 이름을 가진 metadata locking
- 이름을 가진 lock을 획득한 후 해제할 때까지 다른 세션은 이 lock을 획득할 수 없도록 한다. 
- **주의점** 
  - 트랜잭션이 종료될 때, lock이 자동으로 해제되지는 않는다.
  - 별도의 명령어를 통해 해제를 수행하거나, 선점시간이 끝나야 lock이 해제된다. 
- `Pessimistic Lock`과 유사하다.
  - 그러나, `Pessimistic Lock`은 Row 나 Table 단위로 Lock을 걸지만, 
  - **`Named Lock`은 metadata에 락킹을 하는 방법**이다.

<br>

---

### Ref.

- https://dev.mysql.com/doc/refman/8.0/en/
- https://dev.mysql.com/doc/refman/8.0/en/locking-functions.html
- https://dev.mysql.com/doc/refman/8.0/en/metadata-locking.html
