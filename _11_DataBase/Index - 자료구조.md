# 인덱스의 자료구조

## Hash Table

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRpMoO%2FbtqKMzdg9TX%2FXYkGt2kqE0hr9rqhHx3o3K%2Fimg.png)

- `해시 테이블`: `Key-Value` 구조로 데이터를 저장하고 빠른 데이터 검색이 필요할 때 유용하다. 
- 해시 테이블 기반의 DB 인덱스는 컬럼의 값을 Key로, 데이터의 위치를 Value로 사용하여 컬럼의 값으로 생성된 해시를 통해 인덱스를 구현

### Hash Index가 DB의 Index 구조로 적합하지 않은 이유

- Hash Index는 데이터 검색시 시간복잡도가 O(1)이므로, 데이터 검색 시 속도가 빠르다.
- 단, ***Hash Index의 경우 등호(`=`)연산에만 특화되었으므로, DB 인덱스에서 사용하기에는 제한적***이다.
- _**데이터가 조금이라도 달라지면 Hash Function은 다른 Hash값을 생성하므로, 부등호 연산(>, <)이 자주 사용되는 DB 검색에선는 Hash Index 가 적합하지 않다.**_
- 예) "나는"으로 시작하는 모든 데이터를 검색하기 위한 쿼리문은 인덱스의 혜택을 전혀 받지 못한다. 따라서 일반적으로 DBMS는 `B+Tree` 자료구조를 이용한다.

# B+Tree Index

### BST

<img src="https://velog.velcdn.com/images/balparang/post/dccce2c7-d551-4c48-b6a1-9a935b0e3f25/image.png" width="500">

### B Tree (B 트리)

<img src="https://velog.velcdn.com/images/balparang/post/0ae593cc-1541-479c-81fc-7a139c8fa418/image.png" width="500">

- BST 구조에서 노드마다 데이터를 2개 이상 저장하여 검색 속도를 강화
- BST 에서는 2번의 이동으로 1~7 이동 가능했지만 B-Tree를 이용하여 두 번의 이동으로 1~13 검색이 가능해졌다.

## B+Tree

<img src="https://velog.velcdn.com/images/balparang/post/12b5d094-5a4c-4d53-a149-bb990ffc5243/image.png" width="500">

<img src="https://velog.velcdn.com/images/balparang/post/0c36112b-1c74-4f73-b1d9-5f8515ce9192/image.png" width="500">

- DB의 인덱스를 위해 **_자식 노드가 2개 이상인 B-Tree를 개선시킨 자료구조._**

> 참고) B+Tree의 B는 Balance!

### B+Tree가 DB Index를 위한 자료구조로 적합한 이유

1. **_항상 정렬된 상태를 유지하여 부등호 연산에 유리하다._**
2. 데이터 탐색뿐 아니라, 삽입/수정/삭제 시에도 항상 O(logN)의 시간 복잡도를 갖는다.  

<img src="https://velog.velcdn.com/images/balparang/post/ed1f3105-d35f-4c6a-8995-391a71cbbe4d/image.png">

`박현지`보다 작은 값들을 가리키는 포인터를 저장 & `박현지`보다 크거나 같은 값들을 가리키는 포인터를 저장

### B+Tree 실제 구조

<img src="https://velog.velcdn.com/images/balparang/post/364b227f-7c26-43f5-808e-405938ee0f80/image.png">

- 리프노드만 데이터를 갖는다. 리프노드가 아닌 노드는 데이터가 아니라 어디로 이동해야할지 가이드(=포인터)만 갖는다. 
- 리프노드에는 데이터들이 `search-key`를 기준으로 정렬되어있다.
- _**리프노드는 LinkedList로 연결되어 범위 검색이 쉬워진다.**_
  - B-Tree 의 경우 범위를 벗어나는 데이터를 검색할 경우 상위 노드로 이동해서 데이터가 있는 노드로 이동해야한다. 
  - B+Tree는 데이터 노드의 범위를 벗어나는 데이터를 검색할 경우 상위 노드로 이동하지 않고 다음 데이터 노드로 이동하면 된다.
- 부등호가 있는 `SELECT WHERE` 절 동작 예시
  - `SELECT * FROM STUDENT WHERE 이름 >= '배준석';` 실행
  - 루트노드 `박현지` 로 이동 
  - `배준석`이 비교대상 `박현지`보다 크므로 오른쪽 포인터 타고 뎁스가 1인 노드로 이동
  - `배준석`이 비교대상 `정재헌`보다 작으므로 왼쪽 포인터 타고 리프노드로 이동
  - 이름이 `배준석` 이상인 데이터를 SELECT 

---

**※ Reference**

- [index가 뭔지 설명해보세요 (개발면접시간) - 코딩애플](https://www.youtube.com/watch?v=iNvYsGKelYs)
- [[Database] 인덱스(index)란? - 망나니 개발자](https://mangkyu.tistory.com/96)
- [[MySQL] B-Tree로 인덱스(Index)에 대해 쉽고 완벽하게 이해하기 - 망나니개발자](https://mangkyu.tistory.com/286)

---

## 면접 질문

### 🆀 데이터를 검색을 할 때 해시 테이블의 시간복잡도는 O(1)이고 B+Tree는 O(logN)으로 더 느린데 왜 인덱스는 해시 테이블이 아니라 B+Tree로 구현되었을까요?

- Hash Index의 경우 등호연산에만 특화되어있다. 
- 조금이라도 다른 데이터에 대해서 다른 해시값을 가지므로, 부등호 연산이 많은 DB검색에는 적합하지 않다.
- B+Tree 의 경우 데이터가 저장되는 리프노드가 LinkedList로 연결되어있다. 따라서 범위 검색시 다음 리프노드로 바로 이동할 수 있다. 
  - 즉, 부등호 연산이 많은 DB검색에 적합하다. 

