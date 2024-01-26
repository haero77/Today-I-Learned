# Java API

<details>
    <summary><b>컬렉션 프레임워크에 대해 설명해주세요.</b></summary>

_👉 "컬렉션은 여러 자료구조 알고리즘을 미리 구현하여 데이터를 효율적으로 처리할 수 있도록 제공하는 자바 API입니다. List, Set, Queue, Map 인터페이스가 컬렉션을 상속하며, 이를 구현하는 클래스를 제공하여 일관된 API를 사용할 수 있다는 장점이 있습니다. List, Set, Queue는 Iterable 인터페이스를 상속하는 Collection을 상속하며, Map 의 경우 key-value 구조로 다른 인터페이스들과 구조가 달라 Iterable을 상속 하지 않습니다."_

### 컬렉션 프레임워크

- 여러 가지 자료구조 알고리즘을 미리 구현하여 데이터를 효율적으로 처리할 수 있도록 제공하는 자바 API이다.
- List, Set, Queue, Map 인터페이스가 Collection을 상속한다.
    - 이를 구현하는 클래스를 제공하여 일관된 API를 사용할 수 있다.

![image](https://user-images.githubusercontent.com/65555299/230928993-9a3ff0e8-a191-427f-9fad-09ea6342e214.png)
![image](https://user-images.githubusercontent.com/65555299/230942240-96a725e6-de59-43ec-8354-515a1dab2a7e.png)

※ Reference

- https://www.nextree.co.kr/p6506/
- https://hudi.blog/java-collection-framework-1/

</details>

---

<details>
    <summary><b>List 인터페이스의 구현체에 대해 설명해주세요.</b></summary>

- 다른 인터페이스와 달리 List 인터페이스는 배열처럼 '순서'가 있다.
- java.util 패키지에서는 ArrayList, LinkedList, Vector, Stack이 있다.
    - ArrayList와 Vector는 배열처럼 데이터가 저장될 때마다 인덱스 부여
    - LinkedList는 데이터가 저장될 때 이전에 저장된 데이터와 이후에 저장된 데이터의 정보를 포함
- `ArrayList`
    - 배열과 거의 유사(동적배열을 사용하기 위해 사용)
    - 데이터 저장은 배열처럼 메모리에 연속적으로 하고, 데이터 접근은 인덱스를 통해 접근
    - 데이터 삽입 시 오른쪽의 데이터를 다음 인덱스로 이동한 후 삽입
    - 데이터 삭제 시 오른쪽 데이터를 다음 이전 인덱스로 이동
    - **_동기화 처리를 지원하지 않는다._**
    - 장단점
        - 장점: 검색 속도가 빠르다.
        - 단점: 데이터 추가, 삭제 시 많은 부하가 생긴다.
- `Vector`
    - ArrayList처럼 ***가변 크기 배열에 데이터를 저장***하며, 데이터를 처리하기 위한 메서드도 같다.
    - **_동기화 처리를 지원한다._**
    - Stack은 Vector를 상속하므로 동기화 처리 지원
- `LinkedList`
    - LinkedList는 ArrayList처럼 데이터를 순서대로 저장되지 않고 임의의 위치에 저장됨(비연속)
    - 데이터들을 하나의 그룹으로 처리하기 위해 **_데이터를 저장할 때 다음 데이터의 주소를 추가로 저장_**

</details>

<details>
    <summary><b>Array와 ArrayList는 어떤 차이점이 있을까요?</b></summary>

<br>

_👉 "Array는 메모리에 한 번 할당되면 크기를 변경할 수 없지만, ArrayList는 크기가 가변이라는 차이점이 있습니다. Array는 기본 타입, 참조 타입 모두 저장 가능하지만 ArrayList는 참조 타입만 저장 가능합니다. 데이터 추가 삭제시에 ArrayList는 주변 데이터를 이동해야하기 때문에 성능면에서 Array가 유리합니다."_

<br>

- `Array`
    - 크기 고정
    - Primitive Type, Reference Type 모두 저장가능
- `ArrayList`
    - 크기 가변
    - Reference Type만 저장 가능
        - 제네릭 타입을 사용하기 때문에 타입 안정성 보장
    - 데이터 추가 삭제시 주변 데이터를 이동해야하기 때문에 Array 보다 성능면에서 불리
    - 기본 용량은 10
        - `private static final int DEFAULT_CAPACITY = 10;`
    - element를 add하려고 할때, capacity가 배열의 길이와 같아지면 일반적으로 기존의 용량 + 기존 용량/2 만큼 크기가 늘어난 배열에 기존의 배열을 copy해서 return

</details>

<details>
    <summary><b>ArrayList는 내부적으로 어떻게 구현되어있나요?</b></summary> 

<br>

- 동적 배열로 구현
- 데이터 삽입 시 오른쪽의 데이터들을 다음 인덱스로 이동한 후 삽입
- 삭제 시 데이터를 삭제한 후 오른쪽의 데이터를 이전 인덱스로 이동

<br>

**🆀 배열로 구현되어있으면 크기가 꽉 찬 경우 일반 배열처럼 예외가 발생할텐데 ArrayList 는 어떻게 무한히 데이터를 받을 수 있을까요?**


- element를 add 할 때, 저장된 데이터 개수가 배열의 용량과 같아지면 grow() 메서드를 호출
- grow() 메서드는 기존 용량 + 기존 용량 / 2 만큼 크기가 늘어난 배열에 기존의 배열을 copy 해서 리턴 (기존 용량의 1.5배)
    - 벡터는 기존 용량의 2배

```java
private Object[] grow(int minCapacity) {
    int oldCapacity = elementData.length;
    if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        int newCapacity = ArraysSupport.newLength(
			oldCapacity,
                        minCapacity - oldCapacity, /* minimum growth */
                        oldCapacity >> 1           /* preferred growth */
        );
        return elementData = Arrays.copyOf(elementData, newCapacity);
    } else {
        return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }
}
```

- https://f-lab.kr/blog/java-backend-interview-1

</details>


<details>
    <summary><b>ArrayList 와 LinkedList를 비교해주세요.</b></summary> 

_👉 "ArrayList는 메모리에 연속으로 저장되므로 메모리 공간이 많이 확보되어야 하지만, LinkedList는 데이터를 메모리에 비연속적으로 저장되므로 메모리를 좀 더 효율적으로 사용합니다. 그러나 LinkedList는 데이터와 함께 노드에 대한 위치 정보까지 저장하므로 ArrayList에 비해 메모리를 더 많이 사용합니다."_

_👉 "ArrayList는 데이터 삽입, 삭제 시 주변의 데이터를 이동해야하므로 부하가 많은 반면에, LinkedList는 노드들의 위치 정보만 변경하면 되므로 성능면에서 유리합니다."_

---

- `ArrayList`
    - 데이터가 순서대로 저장(연속)
    - 데이터 검색면에서는 LinkedList 보다 유리
    - 무작위 접근(Random Access) 가능
- `LinkedList`
    - 데이터가 연속적으로 저장되지 않고 임의의 위치에 저장(비연속)
    - 메모리 효율면에서 연속적인 공간이 필요없으므로, ArrayList보다 유리
    - 무작위 접근 불가능. 순차접근(Sequential Access)만 가능
    - 삽입, 삭제 시 시간이 O(1) + 탐색시간 포함하면 O(n)

</details>

---


<details>   
    <summary><b>Map 인터페이스의 구현체에 대해 설명해주세요.</b></summary>

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

- https://pplenty.tistory.com/17
- https://tecoble.techcourse.co.kr/post/2021-11-26-hashmap-hashtable-concurrenthashmap/


</details>

<details>
    <summary><b>HashTable에 대해 설명해주세요.</b></summary> 

## 해시 테이블(HashTable)

- key, value로 값을 저장하는 자료구조
- Key값의 hashcode를 배열의 고유한 index를 설정
- 실제값이 저장되는 장소를 버킷이라 한다.
- Key값으로 데이터르 찾을 때 해시 함수를 1번만 수행하면 되므로 저장/삭제/조회 속도가 빠름
    - 평균 시간 복잡도: `O(1)`

</details>

<details>
    <summary><b>HashMap과 HashTable을 비교해주세요.</b></summary>
<br>

- HashMap
    - 비동기 처리
    - key, value 로 null을 허용 한다.

- Hashtable
    - 데이터를 처리하는 모든 메서드에 동기처리: get(), put(), remove()
    - key, value 로 null을 허용 안 함


※ Reference
- https://d2.naver.com/helloworld/831311

</details>

<details>
    <summary><b>HashMap과 ConcurrentHashMap은 어떤 차이가 있나요?</b></summary> 

<br>

- HashMap
    - 비동기 처리
    - key, value 로 null값 지원
- ConcurrentHashMap
    - 동기 처리
        - 서로 다른 스레드가 같은 해시 버킷에 접근할 때 동기처리 -> Hashtable보다 성능적으로 우세
    - key, value로 null값 지정 불가능

</details>

--- 

<details>
    <summary><b>Set 인터페이스의 구현체에 대해 설명해주세요.</b></summary>

# Set

- List와 다르게 저장되는 데이터의 순서가 의미없다.
- 해시코드를 이용하여 데이터를 저장하므로 데이터가 저장된 순서를 알 수 없다.
- 해시코드는 데이터를 구분하는 값으로 사용되므로 중복될 수 없다.
- 해시코드는 Object클래스의 hashCode()의 반환값을 사용
- 해시코드를 사용하여 데이터를 처리하므로 컬렉션 중에서 가장 빠르게 검색한다.

### 객체 비교

데이터가 같은지 다른지 비교할 때 hashCode() 와 equals()를 이용한다.

1. Object클래스의 hashCode() 메서드의 반환값으로 비교
2. hashCode()값이 다르면 Object클래스의 equals()메서드를 실행하여 한 번 더 판단.

## 구현체 종류

### HashSet

- 내부적으로 HashMap 인스턴스를 사용하여 요소를 저장
- 저장 순서를 유지하지 않는 데이터의 집합이다.
- 해시 알고리즘(hash algorithm)을 사용하여 검색 속도가 매우 빠르다.

### LinkedHashSet

- 저장 순서를 유지하는 HashSet
- 내부적으로 LinkedHashMap으로 구현

### TreeSet

- Tree와 Set의 특성을 동시에 가진다. 저장되는 데이터 순서 보장되지 않으며, 트리 구조로 저장됨
- 이진 탐색 트리 중 성능을 향상 시킨 Red-Black Tree로 구현되어있다.
- Comparator 구현으로 정렬 방법을 지정 가능
- 내부적으로 TreeMap으로 구현


</details>

<details>
    <summary><b>List와 Set을 비교해주세요.</b></summary>

- List 인터페이스
    - 데이터간 순서 유지
    - 데이터 중복 허용
- Set 인터페이스
    - 데이터간 순서 유지되지 않음
    - 데이터 중복을 허용하지 않음

### 🆀 해시 테이블을 이용하여 Set이 구현된 이유
- Key가 중복되지 않고 데이터 순서가 랜덤이라는 해시테이블이 특징이 Set의 특징과 일치
- 해시테이블에 데이터가 얼마나 많든간에 키를 검색할 때의 시간복잡도는 `O(1)`


**※ Reference**

- [기술 면접에서 list와 set의 차이를 물어보는 이유](https://www.youtube.com/watch?v=CMgpTGs_N_w)

</details>
