# String

<details>
    <summary><b>String 클래스는 final로 선언되어있습니다. 왜 그런걸까요?</b></summary>

<br>


> 👉 String이 불변이 아니라면 String 객체를 공유할 수 없기 때문입니다. String이 가변이라고 하면, 객체를 두 개이상의 참조변수가 참조할 경우, 한 참조변수를 사용하여 String 객체의 리터럴을 변경하면 다른 참조변수 역시 변경된 값을 참조하게 되는 문제가 있습니다. 이 외에도 보안상 String이 가변일 경우 사용자가 특정 파일에 대한 액세스를 얻은 후 PATH를 변경할 수 있게되고 이것은 자친 심각한 오류를 야기할 수 있습니다.  이러한 이유들로 String 객체는 final, 불변이어야합니다.

---

**※ Reference**
- https://www.mimul.com/blog/why-string-class-has-made-immutable-or-final-java/

</details>


<details>
    <summary><b>String을 new 또는 "" 로 생성했을 때의 차이점을 설명해주세요.</b></summary> 

<br>

> 👉 문자열 리터럴과 new 키워드로 생성할 때의 String 객체 생성 방식이 다릅니다. 문자열 리터럴로 String 객체를 생성할 경우, 힙 메모리의 문자열 상수풀에 같은 값을 갖는 객체가 있는지 확인하고 있으면 그 객체의 참조값을, 없으면 객체를 문자열 상수풀에 새로 생성해서 그 참조값을 리턴합니다. 따라서 == 비교시에 true를 리턴합니다. new 키워드를 이용하여 생성할 경우 문자열 상수풀이 아닌 힙메모리에 객체를 생성하므로, == 비교 시 false를 리턴합니다.

## 문자열을 생성하는 3가지 방법

### 1. 문자열 리터럴로 생성하는 경우

- String literal로 객체를 생성하면,
    - 힙 영역의 문자열 상수 풀(String Constant Pool)에 같은 값을 가지는 객체가 있는지 확인한다.
    - 같은 값을 가지는 객체가 있으면 그 객체의 참조값을, 없으면 String Pool에 객체를 생성하고 그 참조값을 리턴한다.
- _**새로운 리터럴을 할당하면 String Pool에 존재하는 객체의 참조값을 리턴하거나, 새롭게 객체를 String Pool에 만들어서 참조값 리턴.**_

👉 **_같은 리터럴로 생성한 String 객체의 == 값이 같다(참조값이 같으므로)._**

### 2. new 키워드로 생성하는 경우

- new 키워드와 String() 생성자로 객체를 생성하는 경우 `String Pool`이 아닌 `힙 영역`에 객체를 생성하고 그 참조값을 리턴

👉 **_같은 값이지만 new 키워드로 생성한 String 객체의 == 값이 다르다(참조값이 다르므로)._**

```java
String a = "aaa";
String b = "aaa";
String c = new String("aaa");
String d = new String("aaa");
```
![image](https://user-images.githubusercontent.com/65555299/230854054-797dcdde-bef2-4c3c-a33b-ae3d1685d293.png)


### 3. `intern()`으로 문자열 생성

- "문자열 리터럴이 생성될 때마다 JVM은 해당 문자열이 문자열 상수 풀에 존재하는지 확인합니다. 문자열 상수 풀에 해당 문자열이 존재하지 않으면, 해당 문자열을 문자열 상수 풀에 저장하고 존재하면 저장하지 않습니다."를 수행하는 메서드
- `intern()` 으로 생성하면 문자열 리터럴로 생성한 것과 같은 결과

```java
public static void main(String args[]) {
  String str1 = "Hello";
  String str2 = new String("Hello").intern();
  String str3 = new String("Hello");

  System.out.println(str1 == str2);
  System.out.println(str1 == str3);
}
```

![image](https://user-images.githubusercontent.com/65555299/230855009-0315ad29-659d-4354-8b29-605c3d4eb995.png)


### String Constant Pool

- Java 버전이 올라감에 따라 Method -> Heap 으로 이동
    - Java 6 이전
        - PermGen 영역에 String Pool 존재
        - PermGen 은 메타 클래스 데이터 등 Method 영역에 존재
        - PermGen 은 사이즈가 고정(32MB ~ 96MB) 👉 String을 많이 사용하면 OutOfMemory 에러 발생
    - Java 7
        - String Pool이 기존의 PermGen에서 Heap으로 이동
        - 고정된 String Pool 사이즈 때문에 생기던 문제(OOM) 해결
    - Java 8
        - PermGen이 Metaspace 로 변경됨(고정 크기 -> 동적 크기)

- 장점
    - 문자열 상수 풀에 같은 문자열 객체가 존재하는 경우 객체를 생성하지 않으므로 메모리 공간 절약
    - 문자열 상수 풀은 문자열 캐싱을 사용하므로, JVM은 문자열이 문자열 상수 풀에 존재하는지 빠르게 확인 가능

※ Reference

- https://sabarada.tistory.com/137
- https://www.baeldung.com/java-string-pool
- https://dololak.tistory.com/718
- https://developer-talk.tistory.com/475



</details>

<details>
    <summary><b>String, StringBuilder, StringBuffer 를 각각 비교해주세요.</b></summary>

<br>

>  👉 먼저 String은 불변 문자열이며, 새로운 값을 할당할 때마다 String 객체가 생성된다는 특징이 있습니다. 그러나 이로인해, 문자열 연산시 새로운 문자열 객체를 생성하고 기존의 객체는 버려지게 됩니다. 이 같은 문제를 해결하기 위해 StringBuilder 와 StringBuffer가 추가 되었고 이 둘은 변경 가능한 문자열입니다. 문자열 연산 등으로 기존 객체의 공간이 부족하게 되면 버퍼크기를 늘리며 유연하게 동작한다는 특징이 있습니다. StringBuffer는 StringBuilder와 다르게 주요 메서드에 synchronized 키워드를 사용하여 Thread Safe 하다는 특징이 있습니다.

- String이 final이 아니라면 Constant Pool 입장에서 String인지 String의 Sub Class 인지 알 길이 없다.

<br>

### String

- 새로운 값을 할당할 때마다 String 객체가 생성된다.
- 문자열은 불변
- String + String + ... + string
    - 문자열 연산 시, 연산 하고 새 String 객체를 만들어 참조
        - JDK 1.5부터 String은 StringBuilder로 변환되기 때문에, 이러한 연산은 최적화 되었다.
    - String 객체 각각의 주솟값이 Stack에 쌓이고, 가비지 컬렉터가 호출되기 전까지 String 객체가 힙 메모리에 쌓인다.

### StringBuilder Vs StringBuffer

- memory에 append하는 방식으로 클래스에 대한 객체를 직접 생성하지 않는다.
- StringBuilder와 StringBuffer는 문자열 연산 등으로 기존 객체의 공간이 부족하게 되는 경우 기존의 버퍼크기를 늘리며 유연한게 동작

- `StringBuilder`
    - 변경 가능한 문자열
    - **_Thead Safe 하지 않다._**
    - 단일 스레드 환경에서는 StringBuffer보다 StringBuilder를 사용하는 것이 성능상 좋다.(StringBuffer는 동기화 처리를 해야하므로)

- `StringBuffer`
    - 변경 가능한 문자열
    - **_Thread Safe 하다(동기화)._**
    - 각 메서드별로 synchronized 키워드로 선언

**※ JDK 1.5 부터 String 의 `+`연산은 컴파일시에 StringBuilder를 사용하여 성능 최적화가 이루어진다.**

- 단, String이 항상 StringBuilder로 변환되는 것은 아니다. (_concat() 메서드 사용시 StringBuilder로 변환되지 않는다_.)
- 한 줄로 선언한 경우 최적화되지만, 여러 줄로 선언한 경우는 연산 시 StringBuilder객체를 매번 생성하기 때문에 성능저하는 여전하다.

```java
//컴파일 전 소스파일
String str2 = "";
        str2 += 0;
        str2 += 1;
        str2 += 2;
		
// 컴파일 이후, 디컴파일한 소스파일
String str2 = "";
    str2 = (new StringBuilder()).append(s1).append("0").toString();
    str2 = (new StringBuilder()).append(s1).append("1").toString();
    str2 = (new StringBuilder()).append(s1).append("2").toString();
```


※ Reference

- [String은 항상 StringBuilder로 변환될까?](https://siyoon210.tistory.com/160)
- [String, StringBuilder, StringBuffer의 차이](https://12bme.tistory.com/42)

</details>
