# Data Type

---

<details>
    <summary><b>Java 의 데이터 타입에 대해 설명해주세요.</b></summary>

> 👉 자바의 자료형은 기본형과 참조형이 있습니다. 기본형 같은 경우 int, char 등 총 8개의 자료형이 있으며, 크기가 작고 고정적이므로 메모리의 스택 영역에 저장됩니다. 기본형을 제외한 나머지 자료형은 모두 참조형입니다. class, array, interface 등이 참조형에 해당하며 크기가 가변적이고 동적이므로, 동적으로 관리되는 메모리의 힙 영역에 저장됩니다.

- 기본 데이터 타입
    - 기본형으로는 int, char, boolean 등이 있다.
        - 정수형: byte, short, int, long
        - 실수형: float, double
        - 논리형: boolean(true/false)
        - 문자형: char
    - 기본형은 크기가 작고 고정적이므로 메모리의 Stack 영역에 저장된다.
- 참조 데이터 타입
    - 기본형을 제외하고는 모두 참조형
    - new 키워드를 이용해 객체를 생성하고, 데이터가 생성된 주소를 참조하는 타입
        - String 과 배열은 new 키워드 없이 생성가능하지만 참조 데이터 타입이다.
    - 참조타입의 종류는 class, array, interface, Enumeration 등이 있음
    - 참조형 데이터는 크기가 가변적이고 동적이므로, 동적으로 관리되는 메모리의 Heap 영역에 저장됨
        - **_데이터는 Heap 영역에 저장되지만 메모리의 주소값은 Stack 영역에 저장된다._**
    - 데이터를 더 이상 참조하는 변수가 없을 때 가비지 컬렉션에 의해 삭제된다.

---

</details>

<details>
    <summary><b>Wrapper 클래스와 Boxing, Unboxing 에 대해서 설명해주세요.</b></summary>

> 👉 Wrapper 클래스란, 기본 자료형의 데이터를 객체로 포장해주는 클래스입니다. java.lang 패키지에 기본 자료형 8개와 매핑되는 래퍼 클래스가 있습니다. 산술 연산을 위해 정의된 클래스가 아니므로 인스턴스에 저장된 값이 불변이라는 특징이있습니다. 기본형 데이터를 참조형 데이터로 변환하는 것을 박싱, 참조형 데이터를 기본형 데이터로 변환하는 것을 언박싱이라고 합니다.

### Wrapper 클래스
- **기본 타입의 데이터를 객체로 포장해주는 클래스**
- 기본 타입의 데이터를 객체로 취급해야하는 경우 사용한다.
    - 예) 매개변수로 객체를 요구할 때, 기본형 값이 아닌 개체로 저장해야할 때 등
- java.lang 패키지에 기본 데이터 타입 8개와 매핑되는 Wrapper 클래스가 존재
- Wrapper 클래스는 산술 연산을 위해 정의된 클래스가 아니므로, 인스턴스에 저장된 값을 변경할 수 없다.

### Boxing, Unboxing

- Boxing
    - **기본 타입의 데이터를 Wrapper 클래스의 인스턴스로 변환하는 과정**
- Unboxing
    - **Wrapper 클래스의 인스턴스에 저장된 값을 기본 타입의 변수에 할당하는 과정**

### AutoBoxing

- **JDK 1.5부터 박싱과 언박싱 과정이 자동으로 처리되는데 이를 autoboxing 이라 한다.**
- 오토박싱은 컴파일 시에 처리 코드가 자동으로 생성되어서 이루어진다.

```java
int n1 = 10; 
Integer obj1 = n1; // 오토박싱. Integer obj1 = Integer.valueOf(n1);
```

```java
Integer obj2 = Integer.valueOf("20");
int n2 = obj2; // 오토언박싱. int n2 = obj2.intValue(); 
```

- Wrapper 클래스와 기본 데이터의 연산도 가능하다. (Wrapper 클래스는 내부적으로 기본 데이터로 변경되어 처리되기 때문)
    ```java
    Integer obj3 = 30; // Integer obj3 = Integer.valueOf(30);
    int n3 = obj + 40; 
    ```

<br>

(참고) Java의 Wrapper class

기본 타입	| 래퍼 클래스
---|---
`byte`|`Byte`
`short`|`Short`
`int`|`Integer`
`long`|`Long`
`float`|`Float`
`double`|`Double`
`char`|`Character`
`boolean`|`Boolean`


</details>

---

<details>
    <summary><b>Java는 Call by Value인가요 Call by Reference인가요?</b></summary> 

<br /> 

> 👉 자바는 메서드 호출 시에 Call by Value 방식을 사용하고 있습니다. 데이터 타입이 기본형인 경우 원시값을 복사해서 인자로 전달하고, 참조형인 경우 주소값을 복사해서 인자로 전달합니다. 이 때문에 함수 안의 지역 변수가 변경되어도 외부 변수의 값은 변경되지 않는다는 특징이 있습니다.

- Java 에서는 메서드를 호출 시 새롭게 지역 변수를 만들어서 값만 복사하고 할당하는 방식이므로, 항상 Call by Value 이다.
    - value 는 기본형 데이터 타입의 값 또는 객체의 주소값
    - 기본 자료형의 경우 변수의 값을 복사해서 인자로 전달
    - 참조 자료형의 경우 객체의 주소값을 복사해서 인자로 전달

---

- **Call by Value**
    - 함수 호출 시 ***인자로 전달되는 변수의 값을 복사하여 함수의 인자로 전달***하는 방식
        - 호출자의 변수와 수신자의 파라미터는 복사된 서로 다른 변수
    - 함수 안에서 지역변수가 변경되어도 외부 변수의 값은 변경되지 않는다.

- **Call By Reference**
    - 함수 호출 시 인자에 참조를 직접 전달하는 방식
        - **호출 쪽의 변수와 수신자의 파라미터는 완전히 동일한 변수**
    - 메서드 내에서 파라미터 변경 시 원본 변수도 변경

</details>

---

<details>
    <summary><b>Java 의 접근제어자는 어떤 것이 있나요?</b></summary> 

> 👉 접근 제어자는 클래스, 변수, 메서드 등 각 요소에 대한 접근 권한을 조절하는 방법입니다. 조건 없이 무조건 접근 가능한 public, 같은 패키지 내에 있거나 상속 받은 경우에만 접근 가능한 protected, 같은 패키지 내에서만 접근 가능한 default, 같은 클래스 내에서만 접근 가능한 private 이 있습니다.

### 접근 제어자

- 접근 제어자
    - 개발할 때, 코드의 어떤 부분은 외부에서 사용할 수 있도록 공개하고 어떤 부분은 비공개로 해서 데이터를 보호해야함. 즉 `접근 제어자`는 각 요소에 접근 권한을 조절하는 방법
    - 클래스와 필드, 메서드 선언시 지정 가능
- 접근 제어자 종류
    - `public` : 조건 없이 무조건 접근 가능
    - `protected` : 같은 패키지 내에 있거나 상속받은 경우에만 접근 가능
    - `default` : 같은 패키지 내에 있을 때만 접근 가능
    - `private` : 같은 클래스 내에서만 접근 가능

### 패키지

- 패키지
    - 클래스를 관리하는 방법이며, 물리적으로는 파일 시스템의 디렉터리를 의미
    - 애플리케이션을 개발할 때 클래스들을 분류하지 않으면 이름이 중복되거나, 어떤 클래스가 어떤 일을 하는지 혼동되는 일이 발생. 따라서 패키지가 필요.

</details>

---

<details>
    <summary><b>클래스, 객체, 인스턴스를 비교해주세요.</b></summary> 


> 👉 객체는 소프트웨어 세계에 구현할 대상이고, 이를 구현하기 위한 설계도가 클래스입니다. 이 설계도에 따라 소프트웨어 세계에 구현된 실체가 인스턴스입니다.


- 클래스
    - 객체를 만들어 내기 위한 `설계도`, 또는 `틀`
    - 연관관계가 있는 변수와 메서드의 집합
- 객체
    - 소프트웨어 세계에 구현할 `대상`
    - OOP 관점에서 클래스의 타입으로 변수가 선언되었을 때를 `객체`라고한다.
    - 객체는 모든 인스턴스를 대표하는 포괄적인 의미를 갖음
- 인스턴스
    - 설계도를 바탕으로 소프트웨어 세계에 구현된 실체
    - 객체가 메모리에 할당되어 실제 사용될 때를 인스턴스라고 부른다.

```java
public class Animal { // 클래스
    ...
}

public class Main {
	
    public static void main(String[] args) {
	    
        Animal cat, dog; // 객체
        
        // 인스턴스화
        cat = new Animal(); // cat은 Animal 클래스의 '인스턴스'(객체를 메모리에 할당)
        dog = new Animal(); // dog은 Animal 클래스의 '인스턴스'(객체를 메모리에 할당)
    }
}
```

Q. 클래스 vs 객체

- 클래스는 설계도, 객체는 설계도를 따라 구현한 모든 대상을 의미

Q. 객체 vs 인스턴스

- 클래스의 타입으로 변수가 선언될 때를 객체, 객체가 메모리에 할당되어 실제 사용할 때를 인스턴스라고 한다.
- 객체는 현실세계에 가깝고, 인스턴스는 소프트웨어 세계에 가깝다.

※ Reference
- http://cerulean85.tistory.com/149

</details>

---

<details>
    <summary><b>오버로딩과 오버라이딩을 비교해주세요.</b></summary> 

> 👉
> - 오버로딩을 메서드를 확장하기 위한 기능입니다. 메서드의 이름은 같지만 매개변수의 자료형이나 개수가 다른 경우가 오버로딩에 해당합니다.
> - 오버라이딩은 기존의 메서드를 덮어쓰기 위해서 사용합니다. 부모 클래스의 메서드 시그니처만 가져와 기능을 재정의하는 것을 의미합니다.

- 오버로딩(Overloading)
    - 메서드의 이름은 같지만 매개변수의 자료형이나 개수가 다른 경우
    - 오버로딩 조건
        - 메서드의 이름은 같아야 한다.
        - 매개변수의 타입 또는 개수가 달라야한다.
    - **_매개변수의 개수와 타입이 같고 리턴타입만 다른 경우는 오버로딩이 불가능_**
- 오버라이딩(Overriding)
    - 부모 클래스의 메소드 시그니처를 복제해서 자식 클래스에서 새롭게 구현
    - 부모 클래스의 기능은 무시하고, 자식 클래스에서 덮어씀
    - **_Overriding을 제대로 구현하려면 접근 제어자, 리턴 타입, 메소드 시그니처(메소드 이름 + 매개변수 타입과 개수)가 모두 동일해야한다._**

</details>

---



<details>
    <summary><b>final 키워드는 왜 사용할까요? 어떤 이점이 있을까요?</b></summary>

> 👉 final 키워드는 변수 또는 메서드, 클래스가 변경 불가능하다, 읽기 전용이다를 명시적으로 나타내기 위해서 사용합니다. 즉, 변경을 제한함으로써 실수와 버그를 줄이기 위해 사용합니다. 예를 들어 로컬 변수에 final 키워드를 선언함으로써 '이 변수는 절대 변경되어서는 안 되는 값입니다.' 라는 정보를 다른 개발자에게 줄 수 있겠습니다. 다만 무조건 적으로 선언하는 것이 아닌, 가독성 등을 고려한 팀의 합의에 따라 작성해야할 필요가 있겠습니다. 개인적으로는 DTO 등 불변 객체를 만들 때 많이 사용하고 있습니다.

※ Ref

https://blog.lulab.net/programming-java/java-final-when-should-i-use-it/#fn:3


</details>


<details>
    <summary><b>final, finally, finalize 에 대해 설명해주세요</b></summary>

> 👉 먼저 final 키워드는 변수 또는 메서드 또는 클래스가 변경 불가능하도록 만듭니다. 변수에 적용 시 기본형 변수의 경우 값의 변경이 불가능하며, 참조형 변수의 경우 참조 변수가 힙 메모리 내의 다른 객체를 참조할 수 없도록 만듭니다. 메서드 적용시 오버라이딩이 불가능하며, 클래스에 적용 시에는 해당 클래스를 상속할 수 없게 됩니다. finally 의 경우 try-catch 블록이 종료 될 때 항상 실행될 코드 블럭을 정의하기 위해 사용합니다. finalize 메서드는 가비지 컬렉션이 더 이상 참조되지 않은 객체를 힙 메모리에서 삭제하는 것을 결정하는 시점에 호출됩니다.

### `final` 키워드

- 개념: 변수 또는 메서드, 클래스가 *변경 불가능*하도록 만든다.
- 변수에 적용 시
    - 기본형 변수: 해당 변수의 값이 변경 불가능하다.
    - 참조형 변수: 참조 변수가 힙 메모리 내의 다른 객체를 참조하도록 변경 불가능하다.
- 메서드에 적용 시
    - 해당 메서드를 오버라이딩할 수 없다.
- 클래스에 적용 시
    - 해당 클래스를 상속할 수 없다.

### `finally` 코드 블럭
- 개념: try-catch 블록이 종료될 때 항상 실행될 코드 블럭을 정의하기 위해 사용

### `finalize()` 메서드
- 개념: 가비지 컬렉션이 더 이상 참조되지 않는 객체를 힙 메모리에서 삭제하겠다고 결정하는 시점에 호출된다.

</details>

<details>
    <summary><b>final 키워드는 컴파일 과정에서 다르게 실행될까요?</b></summary>

> 👉 final 변수의 경우 static 키워드와 같이 사용되는가에 따라 실행과정이 달라집니다. static으로 선언된 변수, 즉 클래스 변수는 초기화 될 때 method area의 Class Variable에 값이 저장됩니다. 이 때 final 로 선언된 변수는 상수로 치환되어 Runtime Constant Pool 에 값이 복사됩니다. 그리고 해당 변수를 사용 시 Constant Pool 에서 값을 읽어들입니다. non-static final 변수의 경우 힙 메모리에 인스턴스가 생성됨에 따라 같이 생성됩니다.

※ Ref

- https://www.holaxprogramming.com/2013/07/16/java-jvm-runtime-data-area/
- [그래서-static-변수는-어디에-저장되는가](https://velog.io/@this-is-spear/%EA%B7%B8%EB%9E%98%EC%84%9C-static-%EB%B3%80%EC%88%98%EB%8A%94-%EC%96%B4%EB%94%94%EC%97%90-%EC%A0%80%EC%9E%A5%EB%90%98%EB%8A%94%EA%B0%80)
- https://www.baeldung.com/java-compile-time-constants

</details>

---

<details>
    <summary><b>제네릭에 대해 설명해주세요.</b></summary> 

> 👉 제네릭이란 데이터 타입을 매개변수로 지정하는 것을 의미하며, 타입 매개변수는(실행 시 인자로 전달하는 타입을 변수로 지정하는 것) 클래스, 인터페이스, 메서드 에 사용가능합니다. 컴파일러의 타입 검사를 통해 타입의 안정성을 보장받을 수 있으며, 불필요한 타입 캐스팅을 없애줌으로써 성능 향상을 가져옵니다.

- 제네릭 타입의 객체는 생성 불가
- static 멤버에 제네릭 타입이 올수 없음
    -  static 멤버는 클래스가 동일하게 공유하는 변수로서 객체가 생성되기도 전에 이미 자료 타입이 정해져 있어야 하기 때문.

</details>

---

<details>
    <summary><b>리플렉션이 무엇이고, 언제 활용 가능한지 설명해주세요.</b></summary> 

> 👉 리플렉션이란, 런타임에 동적으로 특정 클래스를 인스턴화 함으로써 그 클래스의 정보에 접근할 수 있게 하는 자바 API 입니다. 주로 프레임워크나 라이브러리에서 사용됩니다. 이는 사용자가 어떤 클래스를 만들어 사용할지 프레임워크나 라이브러리는 알 수 없기 때문에, 런타임에 리플렉션 API 를 통해 사용자가 요청한 기능을 수행하게 됩니다.

- 구체적인 클래스 타입을 알지 못해도 그 클래스의 정보(메소드, 타입, 변수, ...)에 접근할 수 있게 해주는 자바 기법
- 런타임에 동적으로 특정 클래스를 객체화하여 정보를 분석 및 추출 가능
- 애플리케이션 개발보다는 프레임워크나 라이브러리에서 많이 사용
    - 스프링 어노테이션
    - 롬복
</details>

<details>
    <summary><b>리플렉션의 장단점에 대해 설명해주세요.</b></summary>

> 👉 먼저 장점으로는, 리플렉션을 사용하면 런타임 시점에 사용할 인스턴스를 선택하고 동작시킬 수 있으므로 유연한 프로그래밍이 가능해집니다.
>
> 단 컴파일 시점에 오류를 확인할 수 없다는 점, 접근제어자로 캡슐화된 필드와 메서드에 접근 가능해진다는 단점이 있습니다.

</details>

---

<details>
    <summary><b>자바의 직렬화와 역직렬화에 대해 설명해주세요.</b></summary>

> 👉
> 자바 직렬화란, 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트 형태로 변환하는 기술을 말합니다.   
> 자바 역직렬화란, 직렬화된 바이트 형태의 데이터를 다시 객체로 변환하는 기술입니다.

### 직렬화

- 정의
    - 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트 형태로 변환하는 기술 (예: 객체 -> JSON)
    - (시스템 관점) **_JVM의 메모리에 적재(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변환하는 기술_**

- 조건
    - 데이터 타입이 기본형(primitive type)이어햐 한다.
    - `java.io.Serializable` 인터페이스를 상속하는 객체이어야 한다.

- 장점
    - 자바 직렬화는 자바 시스템에서 개발에 최적화 되어있음
    - 복잡한 데이터 구조를 갖는 클래스의 객체라도, 직렬화의 기본 조건만 지키면 간단하게 직렬화가능함. 역직렬화도 마찬가지. 👉 개발자 입장에서 편하다.

- 방법
    - `java.io.ObjectOutputStream` 객체를 이용
  ```java
  public class Member implements Serializable {
  
    private String name;
    private String email;
    private int age;

    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
  
    // Getter 생략
    @Override
    public String toString() {
        return String.format("Member", name, email, age);
    }
  }
  ```
  ```java
  Member member = new Member("김배민", "deliverykim@baemin.com", 25);
  byte[] serializedMember;
  
  try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
          oos.writeObject(member);
          // serializedMember -> 직렬화된 member 객체 
          serializedMember = baos.toByteArray();
      }
  }
  
  // 바이트 배열로 생성된 직렬화 데이터를 base64로 변환
  System.out.println(Base64.getEncoder().encodeToString(serializedMember));
  ```

- 자바의 직렬화랑 문자열 형태의 직렬화는 다르다
    - 문자열 형태의 직렬화: 객체 ➡️ CSV, JSON
    - 자바 직렬화: 객체 ➡️ 바이트 형태의 데이터

### 역직렬화
- 정의
    - 바이트로 변환된 데이터를 다시 객체로 변환하는 기술(예: JSON -> 객체)
    - (시스템 관점) 직렬화된 바이트 형태의 데이터를 다시 객체로 변환해서 JVM 메모리에 적재하는 기술
- 조건
    - 직렬화 대상이 된 객체의 클래스가 클래스패스에 존재해야 하며, import 되어 있어야 한다.
        - **_직렬화와 역직렬화를 진행하는 시스템이 서로 다를 수 있다는 것을 반드시 고려해야 한다. (같은 시스템 내부더라도 소스 버전이 다를 수 있다.)_**
    - 자바 직렬화 대상 객체는 동일한 `serialVersionUid`를 갖고 있어야 한다.
        - `private static final long serialVersionUid = 1L;`
- 방법

  ```java
  // 직렬화 예제에서 생성된 base64 데이터 
  String base64Member = "...생략";
  byte[] serializedMember = Base64.getDecoder().decode(base64Member);
  
  try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
      try (ObjectInputStream ois = new ObjectInputStream(bais)) {
          // 역직렬화된 Member 객체를 읽어온다.
          Object objectMember = ois.readObject();
          Member member = (Member) objectMember;
          System.out.println(member);
      }
  }
  ```

**※ Ref**
- https://techblog.woowahan.com/2550/

</details>

<details>
    <summary><b>자바의 직렬화는 언제, 어디서 사용되나요?</b></summary>

> 👉 자바의 직렬화는 JVM의 메모리에서만 상주하는 객체 데이터를 영속화(Persistence)할 필요가 있을 때 사용합니다. 시스템이 종료되더라도 없어지지 않으므로 영속화된 데이터이기 때문에 네트워크로 전송도 가능합니다. 이러한 특성때문에, 자바 직렬화는 서블릿 세션이나 캐시 등에 사용됩니다.

### 🆀 자바에서도 CSV, JSON을 사용하면 되지, 자바 직렬화를 써야하는 이유가 있나요?

> 👉 네 사실 이부분은 저도 정답이 없다고 생각하며, 목적에 따라 선택하면 된다고 생각합니다. 자바 직렬화의 경우, 클래스가 복잡한 데이터 구조를 갖는다고 해도 기본조건(primitive type, Serializable 구현)만 지키면 직렬화, 역직렬화가 모두 가능하므로 개발자 입장에서 더 편리하게 사용가능합니다. 따라서 자바 시스템 간의 데이터 교환이 주목적이면 자바 직렬화를 사용하고, 외부로 데이터를 내보내서 저장 등을 할 필요가 있을 때는 호환성이 좋은 CSV나 JSON을 사용하도록 고려하면 되겠습니다.

</details>

---



---

<details>
    <summary><b>자바의 어노테이션에 대해 설명해주세요.</b></summary> 

> 👉 어노테이션은 자바 소스코드에 주석문처럼 추가하는 부가적인 정보로 메타데이터라고도 합니다. 주로 컴파일러에게 정보를 알려주거나, 실행할 때 별도의 처리가 필요할 때 사용합니다. @interface 키워드를 이용하여 사용자 정의 어노테이션을 선언할 수도 있습니다.

### 어노테이션

- 정의
    - 실행하고는 상관없이 자바 소스코드에 주석문처럼 추가하는 부가적인 정보
    - 서로 다른 이름으로 구성된 정보를 가지는 하나의 단위이며, 이것을 메타데이터(metadata)라고도 함

- 언제 사용하나?
    - 컴파일러에게 정보를 알려줄 때
    - 컴파일할 때와 설치(deployment)시의 작업을 지정할 때
    - 실행할 때 별도의 처리가 필요할 때

- **_어노테이션은 상속 불가능하다._**

- 자바 언어가 제공하는 어노테이션은 3개
    - @Override
    - @Deprecated
    - @SuppressWarnings

- `메타 어노테이션(어노테이션을 선언하기 위한 어노테이션)`은 4개
    - @Target: 어노테이션 적용 대상 지정
    - @Retention: 어노테이션 정보 유지 기간 선언
    - @Documented: '어노테이션에 대한 정보가 JavaDocs(API) 문서에 포함된다는 것' 을 선언
    - @Inherited: 모든 자식 클래스에서 부모 클래스의 어노테이션을 사용 가능하다는 것을 선언

- 사용자 정의 어노테이션
    - `@interface` 키워드를 통해서 한다.
  ```java
  import java.lang.annotation.Retention;   
  import java.lang.annotation.ElementType;   
  import java.lang.annotation.RetentionPolicy;   
  import java.lang.annotation.Target;
     
  @Target(ElementType.Method)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface UserAnnotation{
        public int number();
        public String text() default "This is first annotation"; 
  }
  ```


</details>

---


<details>
    <summary><b>Object 클래스의 equals()와 hashcode()는 무슨 역할을 하나요?</b></summary> 

<br>

> 👉 equals()는 2개의 객체가 동일한지 비교하기 위해 사용합니다. 내부적으로 == 연산자를 이용하여 두 객체의 참조변수값을 기준으로 동일한지 판단합니다. hashcode()는 힙 메모리에 저장된 인스턴스의 주소값을 가지고 해시코드를 만들어 반환하는 메서드입니다. 필드값을 기준으로 동등성을 비교하기 위해 equals()를 오버라이딩를 하여 사용할 때, hashcode()를 같이 오버라이딩하지 않는다면 hash값을 사용하는 컬렉션을 사용할 때 문제가 발생하므로, equals와 hashcode는 항상 같이 오버라이딩해서 사용하는 것이 좋습니다.

### equals()

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```
- 2개의 객체가 동일한지 비교하기 위해 사용한다.
- (구현) 2개의 객체가 참조하는 것이 동일한지 확인
    - ~~판단 기준은 `해시코드값`~~ (더 찾아봐야함)
- java.lang.Object에 정의됨

### hashcode()

- 메모리에 생성된 인스턴스의 주솟값을 가지고 일련번호를 만들어 반환하는 메서드
    - 해시 코드는 인스턴스가 메모리에 생성되는 주솟값을 기초로 만들어지는 만큼 **_서로 다른 인스턴스는 해시 코드값이 같을 수 없다._**
    - 32 bit JVM에서 서로 다른 두 객체는 결코 같은 해시코드를 가질 수 없었지만, 64 bit JVM에서는 8byte 주소값으로 해시코드(4byte)를 만들기 때문에 **_해시코드가 중복될 수 있다._**
    - String 객체의 경우 value를 가지고
- java.lang.Object에 정의됨
- Object 명세에는 equals()를 오버라이딩하면 hashcode()를 오버라이딩하라고 안내되어있다.

### `== 연산자`

- 비교하는 값이
    - 기본 타입: 값을 비교
    - 참조 타입: 주소값을 비교


※ Reference

- https://mangkyu.tistory.com/101
- https://tecoble.techcourse.co.kr/post/2020-07-29-equals-and-hashCode/
- https://sas-study.tistory.com/402
- https://sanseongko.github.io/java-evshash


</details>

<details>
    <summary><b>equals()와 hashcode()는 언제 재정의해야할까요? 또, 왜 같이 오버라이딩 하는게 좋을까요?</b></summary> 

<br>

> 👉 equals와 hashcode 오버라이딩은 객체를 비교할 때 주소값으로 비교하는 것이 아닌, 필드값을 기준으로 두 객체가 동등함을 보장하고 싶을 때 사용합니다. 예를 들어 id 필드값을 갖는 Member 클래스가 있다고 가정해보겠습니다. 그리고 같은 id를 갖는 두 객체를 만들고 equals를 통해 비교하면 두 객체는 주소값이 다르므로 false를 리턴합니다. 그런데 개발자 입장에서는 같은 id를 갖는 객체는 같은 데이터로 취급하고 싶은 상황입니다. 이럴 때 equals를 사용하여 동등성 비교 시 true를 얻을 수 있도록 오버라이딩을 합니다.

> 👉 equals와 hashcode를 같이 오버라이딩 하는 이유는 hash값을 사용하는 컬렉션(HashSet, HashMap, HashTable) 을 사용할 때 문제가 발생하기 때문입니다. 예를 들어 HashSet 구현체에 필드값이 같은 동등한 객체는 중복되지 않게 하나씩만 저장해야하는 경우, equals를 오버라이딩 하였더라도 hashcode를 오버라이딩 하지 않으면 논리적으로 다른 객체로 인식되어 중복 저장되게 됩니다. 따라서 안전하게 동등성을 보장하기 위해서는 equals와 hashcode를 같이 오버라이딩 하는 것이 좋습니다.


**hash 값을 사용하는 컬렉션이 논리적으로 같은지 비교할 때의 과정**
<img width="633" alt="image" src="https://user-images.githubusercontent.com/65555299/230924895-074214ea-dc30-4dc1-b59b-50a8598d441b.png">



</details>

<details>
    <summary><b>서로 다른 인스턴스가 같은 hashcode값을 가질 수 있을까요?</b></summary> 

<br>

> 👉 서로 다른 인스턴스가 같은 hashcode 값을 가질 수 있습니다. 예를 들어 String 객체의 경우 hashcode를 계산할 때 value를 기준으로 계산하는데, 낮은 확률이지만 문자열 내용이 달라도 같은 hashcode를 가질 수 있습니다. 또 동등성 비교를 위해 hashcode를 오버라이딩 하는 경우에도 같은 hashcode를 가질 수 있습니다. 단 이와 같은 경우를 제외하면 일반적으로 다른 인스턴스의 경우 hashcode를 갖게됩니다.

- 32 bit JVM 에서는 해시코드 충돌 없지만, 64 bit JVM에서는 해시코드 충돌 가능

**※ Reference**
- https://leedo.me/36

</details>


