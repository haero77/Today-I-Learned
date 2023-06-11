# 클러스터형 인덱스와 보조 인덱스 

## 클러스터형 인덱스(Clustering Index)

- **_특정 컬럼을 기본키로 지정하면 자동으로 클러스터형 인덱스가 생성_** 되고, 해당 컬럼을 기준으로 정렬된다.
- 즉, **_원본 Table 자체가 정렬된 하나의 Index인 것_**(영어사전처럼 책 내용 자체가 정렬된 것이라고 생각하면 된다.)
- 테이블 당 하나만 생성 가능 
- PK 기준으로 인덱스도 정렬되어있다.
- 테이블 생성시 PK를 지정하면 클러스터 인덱스 생성
  - 이미 생성된 테이블에서 특정 컬럼을 PK로 지정하는 경우 일반적으로 RDBMS 시스템은 해당 컬럼에 대한 인덱스를 만드는데, 이 인덱스는 클러스터 인덱스가 아닌 `Secondary Index`이다. 
    - [인프런 - 질문과답변: 클러스터링이 별도로 인덱스가 생성되는 것인지 헷갈립니다.](https://www.inflearn.com/questions/853149/%ED%81%B4%EB%9F%AC%EC%8A%A4%ED%84%B0%EB%A7%81%EC%9D%B4-%EB%B3%84%EB%8F%84%EB%A1%9C-%EC%9D%B8%EB%8D%B1%EC%8A%A4%EA%B0%80-%EC%83%9D%EC%84%B1%EB%90%98%EB%8A%94-%EA%B2%83%EC%9D%B8%EC%A7%80-%ED%97%B7%EA%B0%88%EB%A6%BD%EB%8B%88%EB%8B%A4)

<img src="https://velog.velcdn.com/images/balparang/post/b1222c7a-79a4-4c6d-93fe-635f9384d242/image.png">

<img src="https://velog.velcdn.com/images/balparang/post/4857ee7e-2be8-4499-af56-fc0b23ce97d9/image.png">

## 보조형 인덱스(Secondary Index)

- 일반 책의 `색인`과 같이 **_별도의 공간에 Index가 생성_** 된다.
- `create index`와 같이 '인덱스 생성하기'를 하거나, 고유키(`unique key`)로 지정하면 보조 인덱스가 생성된다.

<img src="https://velog.velcdn.com/images/balparang/post/766878dc-82a2-4954-93f3-50600f0a9c22/image.png">

위는 `CREATE INDEX idx_name ON STUDENT(이름)` 에 의해 생성된 보조형 인덱스이다.

- 인덱스의 `search-key`가 'ㄱ-ㅎ'순으로 정렬되고, `pointer`는 튜플의 물리적 위치가 저장된다.  
- Secondary Index는 실제로 포인터를 저장하지 않는다. (PK를 참조한다.)


<br>

> 👉 `클러스터형 인덱스`는 **_테이블에 저장된 데이터 자체를 정렬_** 하는 것이고, `보조형 인덱스`는 **_테이블은 건드리지 않으면서 추가적으로 인덱스만 따로 생성하는 것._**
> 테이블이 search-key 를 기준으로 정렬이 되어있으면 클러스터 인덱스, 정렬이 안 되어 있으면 Secondary Index


---
**※ Reference**

- [기출로 대비하는 개발자 전공면접 [CS 완전정복]](https://www.inflearn.com/course/%EA%B0%9C%EB%B0%9C%EC%9E%90-%EC%A0%84%EA%B3%B5%EB%A9%B4%EC%A0%91-cs-%EC%99%84%EC%A0%84%EC%A0%95%EB%B3%B5/dashboard)
- [[MySQL] 인덱스(index), 클러스터/보조인덱스 - 실행계획 포함](https://jie0025.tistory.com/107)
- [[MYSQL] 📚 인덱스(index) 핵심 설계 & 사용 문법 💯 총정리 - 인테이블당](https://inpa.tistory.com/entry/MYSQL-%F0%9F%93%9A-%EC%9D%B8%EB%8D%B1%EC%8A%A4index-%ED%95%B5%EC%8B%AC-%EC%84%A4%EA%B3%84-%EC%82%AC%EC%9A%A9-%EB%AC%B8%EB%B2%95-%F0%9F%92%AF-%EC%B4%9D%EC%A0%95%EB%A6%AC)