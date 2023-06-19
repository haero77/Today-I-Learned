# JVM


<details>
    <summary><b>Java의 컴파일(실행) 과정을 설명해주세요.</b></summary> 

<br>

🗣️
_"javac컴파일러를 이용하여 자바 소스파일을 컴파일 하면 바이트 코드, 즉 실행파일이 만들어집니다.
이후 JVM에 의해 실행될 때, 가장 먼저 클래스로더가 동작하여 실행에 필요한 실행파일을 찾고, 해당 파일의 유효성 검사를 진행합니다. 이후 JVM의 실행엔진에 의해 실행파일은 기계어로 변환되는데, 인터프리터가 코드를 한 줄씩 읽고 기계어를 변환합니다. 이 때 반복되는 코드가 있으면 JIT컴파일러에 의해 해당부분이 전부 네이티브 코드로 변환되고, 이후 인터프리터는 변환된 코드를 바로 사용합니다. 이후에는 OS에 의해 실행됩니다"_


<br>

1. 컴파일
  - 자바 소스파일을 javac 컴파일러를 이용하여 바이트 코드(.class 파일)로 컴파일.

2. 클래스 로딩 & 유효성 검사
  - 프로그램 실행시 클래스로더가 가장 먼저 동작하며, 실행에 필요한 실행 파일(.class 파일)을 찾아준다.
  - Byte code verifier 가 준비된 실행파일의 유효성을 검사
    - 유효하지 않으면 JVM단에서 에러가 발생하여 실행되지 않음
3. 기계어로 변환
  - JVM의 실행엔진(인터프리터, JIT컴파일러)에 의해 기계어로 변환
  - 인터프리터를 통해 바이트 코드를 한 줄씩 기계어로 변환하다가, 반복되는 코드가 있으면 JIT컴파일러를 이용하여 해당 코드를 네이티브 코드로 변환.
  - 인터프리터는 네이티브 코드로 변환된 코드를 바로 사용.
4. 운영체제에 의해 실행

</details>

<details>
    <summary><b>Java 의 장단점에 대해 설명해주세요.</b></summary> 

> 👉 자바의 장점은 JVM 위에서 프로그램이 실행되므로 운영체제에 독립적이고, 객체지향언어로써 기존 코드의 재사용이 편리하다는 장점이 있습니다. 또한 변경사항이 생기면 해당 클래스만 수정하면 되기 때문에, 유지 보수가 쉽고 빠릅니다. 단점으로는 C/C++ 같은 컴파일 언어 대비 비교적 실행속도가 느리다는 단점이 있습니다.

- 장점
    - 운영체제에 독립적
        - JVM 위에서 동작하므로 플랫폼에 종속적이지 않음(운영체제가 달라져도 추가적인 작업 불필요)
    - 객체지향적인 언어
        - 기존 코드를 재사용 가능하고 모듈식 프로그램을 개발 가능
    - 동적 로딩을 지원
        - 애플리케이션 실행시점에 모든 객체가 생성되는 것이 아닌, 객체가 필요할 때 클래스를 동적으로 로딩
        - 변경사항이 생기면 해당 클래스만 수정하면 되기 때문에, 전체 애플리케이션 컴파일 불필요. 유지보수가 쉽고 빠름
- 단점
    - C/C++ 과 같은 컴파일 언어 대비 실행 속도가 비교적 느림
        - Java는 컴파일 후 바이트 코드가 생성되고 JVM에서 기계어로 번역되고 실행되는 과정을 거치므로 컴파일언어 대비 실행속도가 비교적 느림

</details>

<br>

---

## JVM

<details>
    <summary><b>JDK, JRE, JVM에 대해 설명해주세요.</b></summary> 

<br>


🗣️ 먼저 JVM이란, 플랫폼에 종속받지 않고 CPU가 Java를 실행할 수 있게 하는 가상 컴퓨터입니다. 바이트 코드를 OS에 특화된 기계어로 변환하고, 실행하며 따라서 JVM은 플랫폼에 종속적이라는 특징이 있습니다. JRE는 자바 애플리케이션을 실행할 수 있도록 구성된 배포판입니다. JVM과 자바 클래스 라이브러리를 포함합니다. JDK는 JRE와 개발에 필요한 도구인 javac컴파일러, javadoc 등을 필요하여 자바 애플리케이션을 개발할 수 있는 환경을 제공합니다.


<br>

![image](https://user-images.githubusercontent.com/65555299/231639514-91894f15-939d-41c3-9793-2890818bea44.png)

### JVM(Java Virtual Machine)

- **_플랫폼(OS)에 종속받지 않고 CPU가 Java를 인식, 실행할 수 있게 하는 가상 컴퓨터_**
- Java 바이트 코드(.class 파일)를 OS에 특화된 기계어로 `변환`(인터프리터와 JIT컴파일러) `실행`한다.
- Java 바이트 코드가 실행될 수 있는 `가상 환경`을 제공
- 바이트 코드를 실행하는 표준(JVM 자체는 표준)이자 구현체(특정 밴더가 구현한 JVM)다.
    - JVM 밴더: 오라클, 아마존, Azul...
- **_JVM은 특정 플랫폼에 종속적_**
    - 바이트 코드를 특정 OS에 맞게 변환해야하므로

  > JVM, JRE, and JDK are platform dependent because the configuration of each OS is different from each other. However, Java is platform independent.


### JRE(Java Runtime Environment)

> JRE is the implementation of Java Virtual Machine (JVM)
- 자바 애플리케이션을 실행할 수 있도록 구성된 배포판. `JVM + Java Class Library`로 구성
- JVM과 핵심 라이브러리 및 자바 런타임 환경에서 사용하는 프로퍼티 세팅이나 리소스 파일을 가지고 있다. (JVM + 라이브러리)
  - ex) `java.io`, `java.util`
- 개발 관련 도구는 포함하지 않는다.(개발 관련 도구는 JDK에서 제공)

### JDK(Java Development Kit)
- _**JRE + 개발에 필요한 툴(javac 컴파일러, javadoc, ...)**_

</details>

<details>
    <summary><b>JVM의 메모리 구조에 대해 설명해주세요.</b></summary>

- `메소드 영역`
    - 클래스 레벨의 정보(클래스 이름, 부모 클래스 이름, 메소드, 변수) 코드 저장.
    - **모든 쓰레드에서 사용하는 공유 자원**이다.
    - JVM 구동 시작 시 생성, 종료 시 까지 유지
    - ~~Runtime Constant Pool~~
    - (Java8 부터는 static 변수가 힙 영역에 저장)
- `힙 영역`
    - new 연산자를 통해 생성된 객체(=인스턴스. 인스턴스 변수 포함)를 저장.
    - **모든 쓰레드에서 사용하는 공유자원**이다.
    - 객체가 더 이상 쓰이지 않거나 null 선언 시 GC가 청소
- `스택 영역`
    - 쓰레드마다 런타임 스택을 만들고, 메소드 호출을 스택 프레임이라 부르는 블럭을 쌓는다.
    - 쓰레드를 종료하면 런타임 스택도 사라진다.
    - **_스택, PC레지스터, 네이티브 메소드 스택은 특정 쓰레드에 종속되는 자원_**. 쓰레드간 공유하지 않는다.
- PC(Program Counter) 레지스터
    - 쓰레드마다 쓰레드 내 현재 실행할 스택 프레임을 가리키는 포인터가 생성된다.
- 네이티브 메소드 스택
    - 네이티브 메소드 호출할 때 생기는 메서드 스택
    - https://javapapers.com/core-java/java-jvm-run-time-data-areas/#Program_Counter_PC_Register


</details>

<br>

---

## Class Loader, GC

<details>
    <summary><b>클래스 로더</b></summary> 

# 클래스 로더(Class Loader)

![image](https://user-images.githubusercontent.com/65555299/231683493-323d86f0-ea99-40e1-aec6-215a2efb9e1a.png)

- 클래스 로더:
    - JRE의 일부분으로, 동적으로 자바 클래스를 JVM에 로드한다.
    - 클래스 로더는 실행에 필요한 클래스들을 찾아서, 메모리에 탑재시키는 역할
    - 로딩, 링크, 초기화 과정을 통해 클래스를 메모리에 적재시키고, 애플리케이션에서 클래스를 사용할 준비를 한다.

## 클래스 로더의 종류

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FAbVQB%2FbtrsH92x5AR%2FjFLGOD4KiwPEiBUW81RDx1%2Fimg.png) <br>

_클래스 로더의 구조_

<br>

![image](https://user-images.githubusercontent.com/65555299/231696516-1effe824-aba5-4b8c-a01f-954a50d6ae6f.png) <br>

_`jdk.internal.loader.ClassLoaders`에 정의된 클래스 로더의 이름_

<br>

### 1. Bootstrap Class Loader

- **_JVM 시작 시 가장 최초로 실행되는 클래스 로더. `java.lang.ClassLoader`를 로드한다._**
- **_JVM을 구동시키기 위한 가장 필수적인 라이브러리(예: java.lang.Object)를 JVM에 탑재시키는 역할_**
- 모든 클래스 로더의 부모로, 최상위 우선순위를 가진다.
- 가장 상위의 클래스 로더이므로, 다른 클래스 로더와는 다르게 OS에 맞게 네이티브 코드로 작성되었다.
- `JAVA_HOME\lib`에 있는 코어 자바 API를 제공한다.
    - https://openjdk.org/groups/core-libs/

### 2. Extension Class Loader(= Platform Class Loader)

- Bootstrap Class Loader 의 자식 클래스
- [jdk.zipfs](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.zipfs/module-summary.html) 등 **_확장 자바 클래스들을 로드한다._**
- `JAVA_HOME\lib\ext` 폴더 또는 `java.ext.dirs` 시스템 변수에 해당하는 위치에 있는 클래스를 읽는다.

### 3. System Class Loader(= Application Class Loader)

- **_자바 프로그램 실행 시 지정한 클래스패스_**(애플리케이션 실행할 때 주는 `-classpath` 옵션 또는 `java.class.path` 환경 변수의 값에 해당하는 위치)**_의 클래스 파일 또는 jar에 속한 파일들을 로드한다._**
- 즉, 개발자가 만든 .class 파일을 로드한다.




# 클래스 로더 동작 과정

## 로딩(loading)

- 클래스 로더가 .class 파일을 읽고 그 내용에 따라 적절한 바이너리 데이터를 만들고 `메소드 영역`에 저장
- 이 때 메소드 영역에 저장되는 데이터
    - FQCN(Fully Qualified Class Name): 클래스가 속한 패키지명을 모두 포함한 이름
    - 클래스, 인터페이스, Enum(이늄)
    - 메소드와 변수
- 로딩이 끝나면 해당 클래스 타입의 `Class 객체`를 생성하여 `힙 영역`에 저장
  ```java
  public static void main(String[] args) {
    // Class 타입의 인스턴스가 해당클래스.class에 저장 
    Class<WhiteShip> whiteShipClass = WhiteShip.class; 
  }

### 클래스 로더 세부 동작방식

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F9945353F5BE909E60D)

- JVM의 메소드 영역에 클래스가 로드되어 있는지 확인. 로드되어있는 경우 해당 클래스를 사용.
- 메소드 영역에 클래스가 로드되어 있지 않은 경우, 시스템 클래스 로더에게 클래스 로드 요청
- 시스템 클래스 로더는 확장 클래스 로더에게 요청을 위임
- 확장 클래스 로더는 부트스트랩 클래스 로더에게 요청을 위임
- 부트스트랩 클래스로더는 부트스트랩 Classpath(JDK/JRE/LIB)에 해당 클래스가 있는지 확인한다. 클래스가 없으면 확장 클래스 로더에게 요청을 넘긴다.
- 확장 클래스 로더는 확장 Classpath(JDK/JRE/LIB/EXT)에 해당 클래스가 있는지 확인한다. 클래스가 존재하지 않을 경우 시스템 클래스 로더에게 요청을 넘긴다.
- 시스템 클래스 로더는 시스템 Classpath에 해당 클래스가 있는지 확인한다. 클래스가 존재하지 않는 경우 ClassNotFoundException을 발생시킨다.

```java
public static void main(String[] args) {
    ClassLoader classLoader = App.class.getClassLoader();
    System.out.println(classLoader);
    System.out.println(classLoader.getParent());
    System.out.println(classLoader.getParent().getParent());
}

// 실행 결과
jdk.internal.loader.ClassLoaders$AppClassLoader@6a6824be
jdk.internal.loader.ClassLoaders$PlatformClassLoader@75bd9247
null
```

## 링크(linking)

- 링크 과정은 로드된 클래스 파일들을 검증하고, 사용할 수 있게 준비하는 과정이다. Verify, Prepare, Resolve 세 단계로 이루어진다.
- **Verify**
    - `.class 파일`이 유효한지 체크한다.
    - 유효하지 않으면 실행하다가 JVM단에서 에러가 나니까 앱이 죽는다.
- **Prepare**
    - static field에 필요한 메모리를 할당하고, 이를 기본값으로 초기화한다.
    - 기본값으로 초기화된 static field는 링크 과정 이후의 `Initialization` 과정에서 코드에 작성된 초기값으로 변경된다.
- **Resolve(optional)**
    - 심볼릭 메모리 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체한다.
    - 메소드 영역에 있는 실제 레퍼런스로 교체하는 것은 링크 과정에서 일어날 수도 있고 일어나지 않을 수 있다(optional).
  ```java
  public class App {
  
      // Symbolic Reference. 논리적인 레퍼런스.
      // 코드를 읽었다 하더라도 실제 사용되기 전까지 실제 인스턴스를 가리키지는 않는다.
      Book book = new Book();
  
      public static void main(String[] args) {
      }
  }
  ```

## 초기화(initialization)

- Static 변수의 값을 할당한다. (static 블럭이 있다면 이때 실행된다.)


<br>


## 클래스 로더의 세 가지 원칙

### Delegation Model

- 클래스 로더는 클래스 또는 리소스를 찾기 위해 요청을 받았을 때, 상위 클래스 로더에게 책임을 위임하는 위임 모델을 따른다.
- `시스템 클스 로더`에게 클래스 로드 요청을 할 때, 시스템 클래스 로더가 부모 클래스 로더인 `확장 클래스 로더`에게 요청을 위임하는 것이 위임 모델을 따르는 예이다.

### Unique Classes

- `Delegation Model`에 의해, 항상 위쪽으로 클래스 로드 요청을 위임하기 때문에 클래스가 고유함을 보장할 수 있다.
- 부모 클래스 로더가 클래스를 찾지 못한 경우에만 자식 클래스 로더가 클래스를 찾을 수 있다.

### Visibility Principle

- 하위 클래스 로더는 상위 클래스 로더가 로드한 클래스를 볼 수 있지만, 역으로 상위 클래스 로더는 하위 클래스 로더가 로드한 클래스를 볼 수 없다.
    - 예) System 클래스 로더에 의해 로드된 클래스는 Extension 또는 Bootstrap 클래스 로더에 의해 로드된 클래스를 볼 수 있다.

<br>

**※ Reference**

- https://www.geeksforgeeks.org/classloader-in-java/
- https://www.baeldung.com/java-classloaders
- [[인프런] 백기선 - 더 자바, 코드를 조작하는 다양한 방법](https://www.inflearn.com/course/lecture?courseSlug=the-java-code-manipulation&unitId=23414&tab=curriculum)
- https://tecoble.techcourse.co.kr/post/2021-07-15-jvm-classloader/
- [인파 - 클래스는 언제 메모리에 로딩 & 초기화 되는가](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%ED%81%B4%EB%9E%98%EC%8A%A4%EB%8A%94-%EC%96%B8%EC%A0%9C-%EB%A9%94%EB%AA%A8%EB%A6%AC%EC%97%90-%EB%A1%9C%EB%94%A9-%EC%B4%88%EA%B8%B0%ED%99%94-%EB%90%98%EB%8A%94%EA%B0%80-%E2%9D%93)
- https://steady-coding.tistory.com/593

</details>


<details>
    <summary><b>가비지 컬렉터</b></summary>

# Garbage Collection

가비지 컬렉션이란, 자바의 메모리 관리 방법 중 하나로 JVM의 Heap 영역에 동적으로 할당했던 메모리 중 더 이상 사용하지 않는 객체를 메모리에서 제거하는 것을 말한다.

GC는 간단하게 말하면 ***JVM의 Heap 영역에서 더 이상 사용하지 않는 메모리를 제거하는 것***을 말한다.

### Stop The World

- GC를 수행하기 위해 JVM이 멈추는 현상을 의미
- GC가 수행되는 동안 GC관련 쓰레드를 제외한 모든 쓰레드는 멈춘다.
- 일반적으로 `튜닝`이라는 것을 이 시간을 최소화 하는 것을 말한다.

### GC의 종류

- **Serial GC**
- Parallel GC
- CMS GC
- **G1 GC**
- Z GC

## GC 알고리즘

### Reference Counting

- Garbage 탐색에 초점을 맞춘 알고리즘.
- 각 객체마다 Reference Count를 관리하며, 이 카운트가 0이되면 GC를 수행
- 카운트가 0이되면 바로 메모리에서 제거된다는 장점이 있음
- 단, **_순환 참조 구조에서 Reference Count가 0이 되지 않는 문제가 발생하여 Memory Leak이 발생할 수 있다._**

<img width="583" alt="image" src="https://user-images.githubusercontent.com/65555299/232697045-09bdf539-5f35-410a-9d3d-e28153a6f292.png">

<img width="580" alt="image" src="https://user-images.githubusercontent.com/65555299/232717330-9765b423-bfb8-4c6c-818e-096d7a077f7d.png">

[사진 출처](https://www.youtube.com/watch?v=FMUpVA0Vvjw)

`Reference Count`란, `Root Space`로 부터 몇 번에 걸쳐 해당 객체에 도달할 수 있는지를 나타내는 횟수이다. 여기서 `Root Space`란 객체를 참조하는 변수로써, Stack의 로컬 변수, 메서드 영역의 참조 변수, Native method stack의 C/C++ 로 작성된 JNI 참조이다.

### Mark-and-Sweep

<img width="1055" alt="image" src="https://user-images.githubusercontent.com/65555299/232719048-4909c55e-dbc8-4ff4-bd75-9e790e367c5d.png">

<br>

[사진 출처](https://www.youtube.com/watch?v=FMUpVA0Vvjw)

- Reference Counting의 순환 참조 문제를 해결하기 위한 알고리즘
- 루트에서부터 해당 객체에 접근 가능한지를 메모리 해제의 기준으로 삼는다.
- 루트로부터 해당 객체에 접근 가능(Reachable)하면 마킹하고(`Mark`), 마킹하지 않은 객체는 삭제한다(`Sweep`).
- Sweep이 끝나면 마킹 정보를 초기화 한다.
- `Compact` 작업이 없어 메모리에 비어있는 공간이 충분하지 않을 경우 `Out of Memory` 가 발생할 수 있다.

### Mark-and-Compact

![image](https://user-images.githubusercontent.com/65555299/232724626-c4153a0e-62b8-42cd-8492-87a574c1f598.png)


- Mark-and-Sweep 알고리즘에서 발새앟는 점유 메모리 분산(Fragmentation)을 해결하기 위해 나온 알고리즘
- Sweep 작업 이후 Compact 작업이 추가되어 흩어져 있는 메모리를 모아주는 작업을 진행
- 장점: Compact 작업으로 메모리 효율을 높일 수 있다.
- 단점: Compact 작업과 그 이후 Reference를 업데이트하는 작업으로 인해 오버헤드(Overhead)가 발생할 수 있다.

<br>

## Minor GC와 Major GC

### Heap 영역의 이해

<img width="1324" alt="image" src="https://user-images.githubusercontent.com/65555299/232704614-29fc44a3-64e9-4ba3-ba90-096a44e10a9d.png">

<br>

[(사진 출처)](https://www.youtube.com/watch?v=jXF4qbZQnBc)

<br>

JVM의 Heap 영역은 객체의 생존 기간에 따라 Young Genration(이하 Young 영역)과 Old Generation으로 나뉜다. 생존기간이 짧은 객체는 Young 영역에 속하고, 생존기간이 긴 객체는 Old 영역에 속하게 된다. 객체가 새로 생성되면 Young 영역에 생성되며, Young 영역에 대한 가비지 컬렉션을 `Minor GC`라고 한다. 객체가 Reachable 상태를 유지하여 살아남게 되면 Young 영역에서 Old 영역으로 이동되며, Old 영역에 대한 가비지 컬렉션을 `Major GC(=Full GC)` 라고 한다.


## 일반적인 GC 과정

### Minor GC 과정

1. 맨 처음 객체가 생성되면 Heap의 Eden 영역에 생성된다.
2. 객체가 계속 생성되어 Eden영역이 꽉 차면, Minor GC가 실행된다.
1. Eden 영역의 미사용 객체는 메모리가 해제된다.
2. Eden 영역에서 생존한 객체는 1개의 Survivor 영역으로 이동된다.
3. 1~2번 과정을 반복하다가 Survivor 영역이 가득 차게 되면 Survivor 영역의 생존한 객체를 다른 Survivor 영역으로 이동시킨다. (1개의 Survivor 영역은 반드시 빈 상태가 된다.).
4. 이러한 과정을 반복하여, 오래 생존한 객체는 Old 영역으로 이동(Promotion)한다.

오래 생존한 객체를 Old 영역으로 이동시킬 때, 오래 생존한 것에 대한 기준은 일정해당 객체의 age를 통해 확인한다. age는 객체의 헤더에 저장되고, `Eden -> Survivor` 이동 시, `Survivor -> Survivor` 이동 시에 하나씩 증가한다.

### Major GC 과정

- Young 영역에서 오래 생존한 객체들이 Promotion되어 Old 영역의 메모리가 부족해지면 Major GC 발생
- Young 영역은 크기가 작기 때문에 시스템 성능에 영향이 적은 반면, Old 영역은 크기가 크므로 성능에 영향이 크다.

<br>

## 가비지 컬렉션 종류

### Serial GC

- `Mark-and-Compact`을 이용하여 가비지 컬렉션 수행
- CPU 코어가 1개일 때 사용하기 위해 개발되었으며, _**모든 가비지 컬렉션을 1개의 쓰레드를 이용하여 처리**_
- CPU의 코어가 여러 개인 운영 서버에서 Serial GC를 사용하는 것은 피해야한다.

### Parallel GC

- **_Java 8까지의 default GC_**
- 기본적인 GC 과정은 `Serial GC`와 동일하나, `Parallel GC`는 _**여러 쓰레드를 사용하여 Parallel하게 GC를 수행함으로써 GC의 오버헤드를 상당히 줄여준다.**_

### CMS GC

- _**`Parallel GC`와 마찬가지로 여러 개의 쓰레드를 이용하여 GC를 수행하나, `Serial GC`, `Parallel GC`와는 다르게 `Mark-and-Sweep`을 Concurrent 하게 수행한다**_ (다른 스레드가 실행 중인 상태에서 Mark and Sweep 수행).

- 장점
    - **_Stop The World 시간이 매우 짧다._**
    - 모든 애플리케이션의 응답 속도가 매우 중요할 때 CMS GC 사용
- 단점
    - 다른 GC 방식 보다 메모리와 CPU를 더 많이 사용
    - Compaction 단계가 기본적으로 제공되지 않는다.
        - 즉, 조각난 메모리가 많아 `Compaction`시 다른 GC보다 `Stop The World` 시간이 더 길어지는 문제 발생 가능.

### G1 GC(Garbage First GC)

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdHxPiT%2FbtqU0xWGaDI%2FwriFcFKPHND5pTAsyn47X1%2Fimg.png)

- `CMS GC`의 단점을 보완하기 위해 나온 방식
- **_Java 9부터 default GC_**
- **_Heap을 동일한 크기의 Region으로 나누고, 가비지가 많은 Region에 대해 우선적으로 GC를 수행하는 방식_**
- 각 Region을 역할과 함께 논리적으로 구분(Eden, Survivor, Old)하여 객체를 할당한다.

<br>

**※ Reference**

- [[Java] Garbage Collection(가비지 컬렉션)의 개념 및 동작 원리 (1/2)](https://mangkyu.tistory.com/118)
- [가비지 컬렉터의 원리? 과연 그것만 물은 걸까요?](https://www.youtube.com/watch?v=v1gb397uFC4)
- [[10분 테코톡] 🤔 조엘의 GC](https://www.youtube.com/watch?v=FMUpVA0Vvjw)
- [Weekly Java: 트래픽이 많이 몰리는 이벤트가 예정되어 있을 때, Young Gen과 Old Gen의 비율 고민하기](https://sigridjin.medium.com/weekly-java-%ED%8A%B8%EB%9E%98%ED%94%BD%EC%9D%B4-%EB%A7%8E%EC%9D%B4-%EB%AA%B0%EB%A6%AC%EB%8A%94-%EC%9D%B4%EB%B2%A4%ED%8A%B8%EA%B0%80-%EC%98%88%EC%A0%95%EB%90%98%EC%96%B4-%EC%9E%88%EC%9D%84-%EB%95%8C-young-gen%EA%B3%BC-old-gen%EC%9D%98-%EB%B9%84%EC%9C%A8-%EA%B3%A0%EB%AF%BC%ED%95%98%EA%B8%B0-3adfeca388af)
- [일반적인 GC 내용과 G1GC (Garbage-First Garbage Collector) 내용](https://thinkground.studio/%EC%9D%BC%EB%B0%98%EC%A0%81%EC%9D%B8-gc-%EB%82%B4%EC%9A%A9%EA%B3%BC-g1gc-garbage-first-garbage-collector-%EB%82%B4%EC%9A%A9/)
- [자바의 메모리 관리 방법! 가비지 컬렉션 (Garbage Collection) [ 자바 기초 강의 ]](https://www.youtube.com/watch?v=jXF4qbZQnBc)

</details>

---


<details>
    <summary><b>정적 바인딩, 동적 바인딩</b></summary>

# 정적 바인딩과 동적 바인딩

## 바인딩(Binding)

바인딩이란, 프로그램의 어떤 기본단위가 가질 수 있는 구성 요소의 구체적인 값, 성격을 확정하는 것을 의미한다.

```
int num = 123;
```
위 문장에서

num 는 변수의 이름,
int 는 변수의 자료형,
123 은 변수의 자료값
이라는 변수의 속성의 구체적인 값이다.

위와 같이 이름, 자료형, 자료값에 각각 num, int, 123 이라는 구체적인 값을 할당하는 각각의 과정을 `바인딩`이라고 한다.

### 함수의 바인딩

어떤 코드에서 함수를 호출할 때, 해당 함수가 있는 메모리의 주소로 연결하는 것을 의미한다.

`프로그램 실행 -> 함수 호출 -> 함수가 저장된 주소로 점프 -> 함수 실행 -> 원래 위치로 이동`

<br>

## 정적 바인딩과 동적 바인딩

###  정적 바인딩 (Static Binding)

- 컴파일 시간에 바인딩이 이루어지며, 실행 중 바인딩이 변하지 않고 유지되는 것을 의미한다.
- `static`, `private`, `final` 멤버(메소드와 변수)는 정적 바인딩을 사용한다.
- 오버로딩된 메서드는 정적 바인딩을 사용한다.

###  동적 바인딩 (Dynamic Binding)

- 실행 시간 도중에 바인딩이 이루어지며, 프로그램 실행 도중 바인딩이 변경가능한 것을 말한다.
- 오버라이딩된 메서드는 동적 바인딩을 사용한다.

```java
public class BindingTest {

	public static void main(String[] args) {

		SuperClass superClass = new SuperClass();
		superClass.staticMethod();
		superClass.instanceMethod();

		SuperClass subClass = new SubClass();
		subClass.staticMethod();
		subClass.instanceMethod();
	}
}

class SuperClass {

	static void staticMethod() {
		System.out.println("SuperClass::staticMethod");
	}

	void instanceMethod() {
		System.out.println("SuperClass::instanceMethod");
	}
}

class SubClass extends SuperClass {

	static void staticMethod() {
		System.out.println("SubClass::staticMethod");
	}

	@Override
	void instanceMethod() {
		System.out.println("SubClass::instanceMethod");
	}
}

// 예상 결과
SuperClass::staticMethod
SuperClass::instanceMethod
SubClass::staticMethod
SubClass::instanceMethod
    
// 실제 결과
SuperClass::staticMethod
SuperClass::instanceMethod
SuperClass::staticMethod
SubClass::instanceMethod
```

위 예제를 살펴보자. SubClass는 SuperClass를 상속하며, `instanceMethod()`를 오버라이딩 한다. SuperClass 참조 타입으로 SuperClass와 SubClass 객체를 하나씩 생성했으며, 참조 변수로 각각의 `staticMethod()` 와 `instanceMethod()`를 호출하였다. `subClass.staticMethod()`는 SuperClass 의 staticMethod()를 호출하였으며, `subClass.instanceMethod()`는 SubClass의 instanceMethod()를 호출했다.

**_그렇다면 왜 `staticMethod()`는 SuperClass의 것을 호출하였는데, `instanceMethod()`는 SubClass의 것을 호출한 걸까?_**

### static 메소드는 정적 바인딩이 이루어진다.

static 메소드는 정적 바인딩이 이루어진다. 컴파일러는 SuperClass의 `staticMethod()`가 SubClass에서 오버라이딩 되지 않는 것을 알고 있으므로(_참고: static 메서드는 오버라이딩이 불가능하다._) 컴파일 시점에 static 메소드를 메모리의 메소드 영역에 탑재한다. 참조 변수 subClass가 SuperClass 타입으로 선언되었기 때문에,  `subClass.staticMethod()`를 실행하면 SuperClass에 정의된 `staticMethod()`를 찾고 이미 바인딩된 메모리의 `staticMethod()` 코드를 실행하여 `SuperClass::staticMethod` 처럼 결과가 나오는 것이다.

### (private이 아닌) 인스턴스 메서드는 동적 바인딩이 이루어진다.

private 이 아닌 인스턴스 메서드는 동적 바인딩이 이루어진다. 컴파일 과정에서 컴파일러는 객체의 타입이 아닌 참조 변수로만 객체를 참조하므로, 런타임에 와서야 함수 바인딩이 이루어진다. `subClass.instanceMethod()`를 호출하면 SuperClass의 `instanceMethod()`에 접근한다. 그런데 `instanceMethod()`는 SubClass에서 오버라이딩 되어있으므로 SubClass의 ` instanceMethod()`가 호출된다.

<br>

### static 메서드가 오버라이딩 되지 않는 이유

> Tip: Geeks, now the question arises why binding of static, final, and private methods is always static binding? <br>
> 👉 _Static binding is better performance-wise (no extra overhead is required). The compiler knows that all such methods cannot be overridden and will always be accessed by objects of the local class. Hence compiler doesn’t have any difficulty determining the object of the class (local class for sure). That’s the reason binding for such methods is static._

**※ Reference**

- https://woovictory.github.io/2020/07/05/Java-binding/
- https://medium.com/pocs/%EB%B0%94%EC%9D%B8%EB%94%A9-binding-4a4a2f641b27
- https://www.geeksforgeeks.org/static-vs-dynamic-binding-in-java/

</details>