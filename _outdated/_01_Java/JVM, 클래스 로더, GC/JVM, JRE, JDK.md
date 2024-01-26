# 자바, JVM, JDK 그리고 JRE

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
- 개발 관련 도구는 포함하지 않는다.(개발 관련 도구는 JDK에서 제공)

### JDK(Java Development Kit)
- _**JRE + 개발에 필요한 툴(javac 컴파일러, javadoc, ...)**_
- 오라클은 자바 11부터 JDK만 제공하며 JRE를 따로 제공하지 않는다.

---

## 참고: 자바, JVM 언어, 바이트 코드

### 자바

- 프로그래밍 언어
- **_소스 코드를 작성할 때 사용하는 자바 언어는 플랫폼에 독립적._**
  - 자바 코드를 컴파일한 바이트 코드 자체는 플랫폼에 상관없이 동일하다.
  - 같은 바이트 코드를 갖고 여러 플랫폼에서 실행가능 👉 **_플랫폼에 독립적_**
   >   JVM compiles the bytecode, converts it to the native machine code of the platform where JVM is present, and then executes it. In other words, JVM makes execution of Java programs platform independent. Although, java programs executes in a two step process which is slower. But **_we have same executables (bytecodes) for different platforms._** <br> 
  >   ※ https://www.quora.com/Can-JVM-of-an-OS-understand-the-byte-code-of-a-different-OS
- JDK에 들어있는 자바 컴파일러(javac)를 사용하여 바이트코드(.class파일)로 컴파일 할 수 있다.
- 자바 유료화?
    - 오라클에서 만든 Oracle JDK 11 버전부터 상용으로 사용할 때 유료

### JVM 언어
- JVM 기반으로 동작하는 프로그래밍 언어
    - 컴파일 결과가 .class 파일이면 JVM에서 실행가능
    - 클로저, 그루비, Kotlin, Scala

### Java bytecode

- 자바 바이트 코드: JVM이 이해할 수 있는 언어로 변환된 자바 소스 코드

> 바이트코드(Bytecode, portable code, p-code)는 특정 하드웨어가 아닌 가상 컴퓨터에서 돌아가는 실행 프로그램을 위한 이진 표현법이다. 하드웨어가 아닌 소프트웨어에 의해 처리되기 때문에, 보통 기계어보다 더 추상적이다. - 위키백과

> **💡 바이너리 코드란?** <br>
바이너리 코드 또는 이진 코드라고 함
컴퓨터가 인식할 수 있는 0과 1로 구성된 이진코드

> **💡 기계어란?** <br>
0과 1로 이루어진 바이너리 코드이다.
기계어가 이진코드로 이루어졌을 뿐 모든 이진코드가 기계어인 것은 아니다.
기계어는 특정한 언어가 아니라 CPU가 이해하는 명령어 집합이며, CPU 제조사마다 기계어가 다를 수 있다.

**_👉 CPU가 이해하는 언어는 바이너리 코드, 가상 머신이 이해하는 코드는 바이트 코드_**


<br>

**※ Reference**

- https://www.javatpoint.com/difference-between-jdk-jre-and-jvm
- [JVM에 관하여 - Part 1, JVM, JRE, JDK](https://tecoble.techcourse.co.kr/post/2021-07-12-jvm-jre-jdk/)
- [[JAVA] JVM이란? 개념 및 구조 (JDK, JRE, JIT, 가비지 콜렉터...)](https://doozi0316.tistory.com/entry/1%EC%A3%BC%EC%B0%A8-JVM%EC%9D%80-%EB%AC%B4%EC%97%87%EC%9D%B4%EB%A9%B0-%EC%9E%90%EB%B0%94-%EC%BD%94%EB%93%9C%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%8B%A4%ED%96%89%ED%95%98%EB%8A%94-%EA%B2%83%EC%9D%B8%EA%B0%80)
- https://www.quora.com/Can-JVM-of-an-OS-understand-the-byte-code-of-a-different-OS
- https://www.geeksforgeeks.org/jvm-works-jvm-architecture/
- https://dzone.com/articles/jvm-architecture-explained
- http://blog.jamesdbloom.com/JVMInternals.html
