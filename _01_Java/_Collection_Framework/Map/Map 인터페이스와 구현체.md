# Map 인터페이스와 구현체

### Entry 인터페이스 

- Map 인터페이스에 선언된 내부 인터페이스

## Map의 구현체

### HashMap

- Entry<K, V>의 배열로 저장되며, 배열의 index는 내부 해쉬 함술르 통해 계산된다.
- 내부 hash값에 따라 Key 순서가 정해지므로 특정 규칙없이 출력된다.
- Key와 Value에 null 허용
- 비동기 처리
- 시간복잡도: `O(1)`

### Hashtable

- 모든 메서드에 대해 동기 처리
- Key와 Value에 null을 허용하지 않는다.

### LinkedHashMap

- HashMap을 상속받으며, 
- 입력 순서대로 출력된다.
- 비동기 처리
- 시간 복잡도: O(n)

### TreeMap

- 내부적으로 레드-블랙 트리로 저장됨
- Key값

### ConCurrentHashMap

