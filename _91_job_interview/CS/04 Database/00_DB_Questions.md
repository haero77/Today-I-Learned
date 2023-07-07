# 🎯 트랜잭션

- 트랜잭션
- ACID
- 트랜잭션 격리 수준
- 갱신 손실 문제

<br>

---

# 🎯 데드락 

<details>
    <summary><b>데드락?</b></summary>

- 여러 transaction들이 각각 자신의 데이터에 대하여 lock을 획득한 상태에서,
- 상대방 데이터에 대하여 접근하고자 대기를 할 때 교차 대기를 하게 되면서 서로 영원히 기다리는 상태

</details>

<details>
    <summary><b>🔼 데드락 해결 방법?</b></summary>

- **예방기법**
  - 각 트랜잭션이 시작되기 전에 필요한 데이터를 모두 락킹.
  - 락킹해야하는 데이터가 많은 경우 사실상 모든 데이터를 락킹한 것과 다름이 없기 때문에 동시성이 매우 떨어짐
- **회피기법**
  - 자원을 할당할 때, `timestamp`를 이용하여 데드락이 일어나지 않도록 하는 방식 
  - `Wait-Die`
    - T2가 선점하고 있는 데이터에 
- 탐지/회복 기법
  - ...

</details>

<br>

---

# 🎯 인덱스

- Index
- Clustered Index Vs. Secondary Index
- 모든 컬럼에 Index를 거는 것이 좋을까?
- 왜 해시 구조를 안 쓰고 B+Tree를 쓸까?

<details>
    <summary><b>Clustering Index Vs. Secondary Index</b></summary>

- 클러스터 인덱스
  - 테이블이 생성될 때 PK를 지정하면 생기는 인덱스
  - 원본테이블 자체가 클러스터 인덱스
  - **데이터 추가 삭제 시 원본 테이블 변경**
  

- 보조형 인덱스
  - 별도의 저장 공간 필요
  - `CREATE INDEX` or 고유키로 지정하면 생성 됨
  - **데이터 추가 삭제 시 원본 테이블 변경 X**

</details>

<details>
    <summary><b>전부 인덱스 걸면 성능이 빨라지는 것이 아닐까?</b></summary>

- No!
- `SELECT WHERE` 쿼리에 대해서만 성능향상을 보장
- 데이터 수정 시 오버헤드 발생
- 데이터 중복이 적을수록 인덱스 적용하기 좋다

</details>

<br>

---

# 🎯 NoSQL

<details>
    <summary><b>NoSQL이란?</b></summary>

- 웹에서 대량의 비정형 데이터를 처리하기 위해 등장한 데이터베이스
- 관계형으로 처리될 필요가 없는 데이터들 
- key-value storage system
  - Redis 

</details>

<details>
    <summary><b>NoSQL Vs. RDB</b></summary>

- **데이터 중복 여부**
  - NoSQL은 데이터 중복으로 인해 update시 모든 중복된 데이터를 업데이트 해줘야한다.
  - RDB는 엄격한 스키마로 데이터 중복이 없음

- **새로운 필드 추가**
  - NoSQL은 비교적 자유로움
  - MySQL은 데이터 구조가 유연하지 못함

</details>

<details>
    <summary><b>NoSQL은 언제 사용하면 좋을까요?</b></summary>

- 정확한 데이터 구조가 정해지지 않은 경우
- update가 적고 조회가 많을 때
  - 데이터 중복으로 인해 update시 모든 중복 데이터를 업데이트 해야함

</details>

<details>
    <summary><b>RDB는 언제 사용하면 좋을까요?</b></summary>

- 데이터 구조가 명확하여 변경될 여지가 없는 경우
- 데이터 중복이 없으므로 데이터 update가 잦은 시스템

</details>

<br>

---

# 🎯 정규화


<br>

---

# 🎯 Key

<details>
    <summary><b>참조 무결성 제약 조건?</b></summary>



</details>

<br>

---


# 🎯 Join

<br>

---

