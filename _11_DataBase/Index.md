# 인덱스

**개요**

<img src="https://velog.velcdn.com/images/balparang/post/4ebf9e54-df43-4442-8b22-a4e1e40cb512/image.png" width="400">

위 같은 테이블에서 age가 20인 행을 찾기 위해서 컴퓨터는 모든 row를 순차적으로 탐색할 것이다. 행이 많아진다면 이런 방식은 많은 시간이 소요될 수 밖에 없다.

<img src="https://velog.velcdn.com/images/balparang/post/68a3af2b-f8d9-43c2-8dea-a8145a848b40/image.png" width="400">

따라서 컬럼을 복제해서 정렬해둔 다음에 이분 탐색으로 빠르게 행을 찾아 나간다. 

### 인덱스 

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcBQD97%2FbtqKRtpm2pl%2Frmo7jTbiiE9tsSQsUg0JPK%2Fimg.png">

- 인덱스
  - 자료를 쉽고 빠르게 찾을 수 있도록 만든 데이터 구조. 책의 색인을 통해 원하는 정보를 빠르게 찾는 것처럼 인덱스도 그와 같은 역할
- 데이터베이스의 Index
  - _**추가적인 쓰기 작업과 저장 공간을 활용하여 데이터베이스 테이블의 검색 속도를 향상시키기 위한 자료구조**_ 
  - 원하는 데이터를 빨리 찾기 위해 **_투플의 키 값에 대한 물리적 위치_** 를 기록해둔 자료구조이다.

### Index를 사용하는 이유

- Table에 데이터를 지속적으로 저장하게 되면 내부적으로 순서 없이 쌓이게 된다.
- 특정 조건을 만족하는 데이터를 찾고자 WHERE절을 사용한다면 Table의 ***튜플을 처음부터 끝까지 모두 접근하여 검색조건과 일치하는지 비교하는 과정(=`Table Full Scan`)*** 이 필요하다.
- 특정 컬럼에 대한 Index를 생성해 놓은 경우 해당 속성에 대하여 `search-key`가 정렬되어 저장되어 있기 때문에 조건 검색(SELECT ~ WHERE) 속도가 굉장히 빠르다.

## Index 구조 

- 인덱스는 B Tree, B+Tree, Hash, Bitmap 등으로 구현 가능
  - 일반적인 RDBMS는 B+Tree를 주로 사용
- 인덱스를 생성하면 **_특정 컬럼의 값을 기준으로 정렬하여 물리적 위치와 함께 별도 파일에 저장_** 된다. 
  - 리프 노드에는 해당 데이터의 저장 위치에 대응하는 rowID(RID, Row Identify, 테이블의 행에 대한 논리적 위치)를 가지고 있어 찾고자 하는 행을 바로 찾을 수 있다.
- 인덱스에 저장되는 속성 값을 `search-key`값이라고 하고, 실제 데이터의 물리적 위치를 저장한 값을 `pointer` 라고 한다.
- **_인덱스는 순서대로 정렬된 `search-key`값과 `pointer`값만 저장하기 때문에 table 보다 적은 공간을 차지한다._**

### Index의 관리

DBMS는 index를 항상 정렬된 상태를 최신으로 유지해야 원하는 값을 빠르게 탐색 가능. 따라서 `INSERT`, `UPDATE`, `DELETE`문이 수행된다면 다음과 같은 연산이 추가적으로 필요하고 그에 따라 오버헤드가 발생한다.  

- `INSERT`: 새로운 데이터에 대한 인덱스를 추가한다.
- `UPDATE`: 기존의 인덱스를 `사용하지 않음`으로 처리하고, 갱신된 데이터에 대해 인덱스를 추가한다.
- `DELETE`: 삭제하는 데이터의 인덱스를 `사용하지 않음`으로 처리


## 인덱스의 장단점

### 장점

- **_최대 장점은 검색 속도 향상(`SELECT~WHERE~`)이다._** 
  - 데이터 검색 시 모든 튜플을 순차적으로 검색, 비교하는 `Table Full Scan`을 하지 않고 인덱스에 정렬된 데이터를 통해 빠르게 원하는 튜플에 접근 가능
- SELECT문 외에도 UPDATE문이나 DELETE문의 성능도 함께 향상된다. 해당 연산을 수행하려면 해당 대상을 조회해야만 작업을 할 수 있기 때문
  ```sql
  // 'haero'라는 이름을 업데이트 해주기 위해서는 'haero'를 조회해야 한다.
  UPDATE USER SET NAME = `haero77` WHERE NAME = 'haero';
  ```

### 단점 2가지 

1. 추가 저장 공간 필요
   - Index를 생성하면 Index 자료구조를 위한 공간이 추가적으로 필요.
   - 보통 테이블 크기의 10% 공간을 차지.
2. 느린 데이터 변경 작업 
   - 검색이 아닌 데이터 변경 작업, 즉 `INSERT`, `UPDATE`, `DELETE`가 자주 발생하면 성능이 나빠질 수 있다. 
   - 보통 B+Tree 구조의 경우 데이터를 변경할 때마다 트리 구조가 변경될 수 있다. 즉, **_인덱스를 재구성하기 위한 오버헤드가 발생_** 한다.  

<br>

# 기타 

### 인덱스는 언제 사용하면 좋을까?

- `INSERT`, `UPDATE`, `DELETE가` **_자주 발생하지 않는 컬럼_**
  - 삽입, 수정, 삭제가 빈번한 속성에 인덱스를 걸면 인덱스의 크기가 비대해져서 성능이 오히려 저하되는 역효과 발생
  - `UPDATE`와 `DELETE`는 기존의 인덱스를 삭제하지 않고 `사용하지 않음` 처리를 한다.
    - 어떤 테이블에 `UPDATE`, `DELETE`가 자주 발생한다면 실제 데이터는 10만 건이지만, 인덱스는 100만 건이 넘어가므로 SQL문 처리 시 비대해진 인덱스에 의해 오히려 성능 하락 가능성 있음.
- JOIN이나 WHERE 또는 ORDER BY에 자주 사용되는 컬럼

### 기본키는 인덱스가 필요없다. 

Primary Key의 경우 자동으로 정렬이 되어있기 때문에 굳이 인덱스를 만들 필요 없다. 이것을 `클러스터형 인덱스`라고 한다. 
 
## ⭐️⭐️ 인덱스를 어느 컬럼에 사용하는 것이 좋을까? 

- 인덱스는 `WHERE` 절에서 ***자주 조회되고, 수정 빈도가 낮으며, 카디널리티는 높고, 선택도가 낮은 컬럼***을 선택해서 설정하는 것이 가장 좋다.  

| 기준                 | 적합성                          |
|--------------------|------------------------------|
| 카디널리티(Cardinality) | 높을수록 적합(데이터 중복이 적을수록 적합)     |
| 선택도(Selectivity)   | 낮을수록 적합                      |
| 조회 활용도             | 높을수록 적합(WHERE절에서 많이 사용되면 적합) |
| 수정 빈도              | 낮을 수록 적합                     |

 > 💡 `카디널리티`란?
 >   - ***데이터가 중복되지 않는 정도***를 뜻한다. 데이터가 중복되지 않을수록 카디널리티가 높다.
 >   - 예) 주민등록번호는 중복되는 값이 없으므로 카디널리티가 높다.
 >   - 예) 성별의 경우 [Male/Female] 값이 중복되어 카디널리티가 낮다.
 
 > 💡 `선택도`란?
 >   - ***데이터에서 특정 값을 잘 골라낼 수 있는 정도***를 뜻한다.
 >   - 선택도가 1이면 모든 데이터가 unique함을 의미한다.

### Index를 효과적으로 사용하는 방법

- SELECT WHERE 절에 자주 사용되는 컬럼에 대해 인덱스르 생성하는 것이 좋다. 
  - 인덱스는 `SELECT~ WHERE~` 절에서 검색 성능을 향상시키기 위해서 사용한다. 따라서 조회가 발생하지 않으면 인덱스를 사용할 필요가 없다.
- 데이터 수정 빈도가 낮을수록 적합하다. 
  - `INSERT`, `UPDATE`, `DELETE` 작업 시 데이터에 변화가 생기므로 인덱스를 재구성하는 오버헤드가 발생한다.
- 데이터 중복이 적은 컬럼에 사용하자
  - 데이터 중복이 많은 컬럼은 인덱스 효과가 별로 없다. 
  - 성별의 경우 종류가 Male, Female 두 가지 밖에 없으므로 인덱스를 생성하지 않는 것이 좋다.
  - `선택도(Selectivity)`가 낮을 때 유리(보통 5~10% 이내). 
    - ~~🤔 데이터가 이미 잘 골라진다면 인덱스를 사용해서 얻는 효과가 미미하므로?~~ 
- 데이터의 양이 많을 수록 인덱스로 인한 성능향상이 더 크다. 
  - 데이터가 10개 밖에 되지 않으면 `Full Table Scan` 시간이 얼마 걸리지 않는다. 이런 경우 굳이 인덱스를 사용할 필요가 없다. 
  - 데이터의 양이 적다면 index의 혜택보단 손해가 더 클 수 있다.
- Join 조건으로 자주 사용되는 컬럼의 경우
- 한 테이블에 인덱스가 너무 많으면 데이터 수정 시 소요되는 시간이 너무 길어질 수 있다. (테이블 당 4~5개 정도 권)

---

**※ Reference**

- [index가 뭔지 설명해보세요 (개발면접시간) - 코딩애플](https://www.youtube.com/watch?v=iNvYsGKelYs)
- [[Database] 인덱스(index)란? - 망나니 개발자](https://mangkyu.tistory.com/96)
- [[MySQL] B-Tree로 인덱스(Index)에 대해 쉽고 완벽하게 이해하기 - 망나니개발자](https://mangkyu.tistory.com/286)

---

### 추가적으로 공부해 볼 것

- 왜 JOIN절에 인덱스를 사용하는 것이 좋을까?
- 인덱스 Random Access

---

## 면접 질문

### 🆀 인덱스가 무엇이고, 왜 필요한지 설명해 주세요.

- 인덱스는 검색성능을 높이기 위해 사용하는 자료구조. 
- 추가적인 쓰기 작업과 저장 공간을 활용하여 DB 테이블의 검색 속도를 향상.
- 인덱스를 사용하면 모든 튜플을 처음부터 끝까지 검색조건과 일치하는지 비교하는 과정인 `Table Full Scan`을 하지 않고 원하는 튜플에 바로 접근이 가능하므로 조건 검색 속도가 굉장히 빠르다는 장점이 있다.   
- 단, 인덱스를 저장하기 위한 `추가 저장 공간이 필요`하고, 데이터가 자주 변경되면 그만큼 인덱스를 자주 업데이트 해야하므로 성능 저하가 있을 수 있다는 단점이 있다.

### 🆀 인덱스는 내부 구조가 어떻게 되어 있나요? (어떻게 동작하나요?)

- 컬럼 값을 `키값(search-key)`으로 가지고 튜플의 물리적 위치인 `포인터`를 Value로 가져서 인덱스를 이용해 튜플에 바로 접근 가능
- B+Tree 구조를 이용하여 탐색범위를 좁혀나가며 조건과 일치하는 데이터를 찾고, 포인터를 이용해 실제 데이터가 저장된 튜플에 접근한다.

### 🆀 기본키는 인덱스라고 할 수 있을까요? 그렇지 않다면, 인덱스와 기본키는 어떤 차이가 있나요?

- PK는 자동으로 정렬되어 있다. 따라서 테이블 자체를 하나의 인덱스로 볼 수 있다. 실제로 컬럼을 PK로 지정하면 클러스터 인덱스가 자동으로 생성된다. 
- 기본키와 인덱스를 구분하자면, 물리적으로 저장되는 위치가 다르다.

- 🆀 _"그렇다면 외래키는요?"_
  - 외래키는 인덱스로 볼 수 없다. 외래키가 정렬되어있음을 보장할 수 없기 때문.

### 🆀 인덱스를 쓰면 성능이 좋아지니까 모든 컬럼에 인덱스를 사용하면 성능이 더 좋겠네요?

- 그렇지 않다. 
- ***인덱스는 `SELECT WHERE`절에 대해서만 성능향상***을 해준다. 따라서 조건 검색 빈도가 낮다면 사용할 이유가 없다. 
- 데이터 수정 시 모든 인덱스를 업데이트(+정렬)해야 하기 때문에 오버헤드로 인한 성능저하를 초래한다. 
- 인덱스를 생성할 때마다 저장공간도 차지하므로 무분별하게 생성해서는 안 된다.
- 따라서 추가 저장공간과 데이터 업데이트시 소요되는 추가 시간 등을 복합적으로 고려하여 ***조건 검색 성능향상이 더 큰 이득이 된다고 판단되는 컬럼만 인덱스로 지정***하는 것이 좋다

### 🆀 그럼 인덱스를 어느 컬럼에 사용하는 것이 좋을까요?

- `조회 빈도가 높고`, `수정 빈도는 낮으며`, `데이터 중복이 적은` 컬럼에 사용하는 것이 좋다.
- 조회 빈도가 낮으면 사용이유 X
- 수정 빈도가 높으면 오버헤드가 크다. 
- 데이터 중복이 많으면 데이터를 정렬해봤자, 탐색 범위 축소에 크게 도움이 되지 않는다.
  - 즉, 탐색 범위를 축소해나가면서 검색하는 B+Tree 구조의 이점이 없다. 

### 🆀 회원의 정보를 저장하는 회원 테이블이 있고, 성별 컬럼이 있다고 가정해보겠습니다. 이 성별 컬럼에 인덱스를 걸어주는 것이 좋을까요?

- 그렇지 않다. 
- 성별처럼 남녀 두 종류로만 나눠지는 경우에는 데이터 중복이 많게 된다. (Male, Female로만 저장되므로)
  - 즉, 데이터 중복이 많으면 카디널리티는 낮고, 선택도는 높다(데이터가 잘 안골라진다).
  - 이런 경우에는 인덱스가 주는 이점이 매우 적다. 
  - _**절반씩 중복되는 성별 데이터를 정렬해봤자, 탐색 범위 축소에 크게 도움이 되지 않는다.**_ 
    - _같은 데이터가 너무 많은데 어떻게 탐색 범위를 축소하겠는가!_
- 저장공간 차지와 성능 저하를 유발하는 것이 더 성능 저하를 야기하므로 인덱스를 생성하지 않는 것이 좋다.

### 🆀 true 또는 false 값을 갖는 컬럼에서, true 1%, false 99%의 비율로 구성된 상황에서는 인덱스를 거는게 좋을까요?

- 데이터가 true/false 로만 나눠지므로 데이터 중복이 많다.
  - 즉, 카디널리티(데이터가 중복되지 않는 정도)는 매우 낮다.
- **_인덱스를 통한 데이터 탐색 범위 축소 효과가 미미하다._**
- 반면에 저장공간과 인덱스 수정 시 인덱스 업데이트(+정렬)로 인한 성능 저하를 고려하여 인덱스를 생성하지 않는 것이 좋다.

### 🆀 ORDER BY/GROUP BY 연산의 동작 과정을 인덱스의 존재여부와 연관지어서 설명해 주세요.