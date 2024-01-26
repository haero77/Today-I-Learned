# Map 인터페이스와 구현체

### Entry 인터페이스 

- Map 인터페이스에 선언된 내부 인터페이스

## Map의 구현체

### HashMap

- Entry<K, V>의 배열로 저장되며, 배열의 index는 내부 해쉬 함술르 통해 계산된다.
- 데이터 순서가 의미없음: 내부 hash값에 따라 Key 순서가 정해지므로 특정 규칙없이 출력된다.
- Key와 Value에 null 허용
- 비동기 처리
- 시간복잡도: `O(1)`

### LinkedHashMap

- HashMap을 상속받으며, 
- 입력 순서대로 출력된다.
- 비동기 처리
- 시간 복잡도: O(n)

### TreeMap

- 내부적으로 레드-블랙 트리로 저장됨
- Key값이 기본적으로 오름차순 정렬되어서 출력
- 키값에 대한 Comparator 구현으로 정렬 방법 지정 가능

### Hashtable

- 데이터를 처리하는 모든 메서드에 동기처리: get(), put(), remove()  
- Key와 Value에 null을 허용하지 않는다.

### ConCurrentHashMap

- Key와 Value에 null을 허용하지 않는다.
- 서로 다른 스레드가 같은 해시 버킷에 접근할 때만 해당 블록이 잠기게 된다.
    - Entry(Node) 배열 원소별로 동기 처리

※ Reference

- https://d2.naver.com/helloworld/831311
- https://pplenty.tistory.com/17
- https://tecoble.techcourse.co.kr/post/2021-11-26-hashmap-hashtable-concurrenthashmap/
