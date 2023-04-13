# JVM 구조 

<img width="868" alt="image" src="https://user-images.githubusercontent.com/65555299/231662218-6cf4c978-ae6a-4f9a-a640-9605a65a27bb.png">

![image](https://user-images.githubusercontent.com/65555299/231655267-54339336-67ed-4806-8d1c-b3d0c74dc531.png)



- 실행과정 요약
  - 클래스 로더가 코드를 읽어서 메모리 영역에 특성에 따라 배치를 한다.
    - 예: static은 메소드 영역에 배치 
  - 실행 할 때, 쓰레드가 만들어지고 쓰레드에 맞게 스택, PC 레지스터, 네이티브 메소드 스택 등이 만들어진다
  - 실행 엔진을 통해 바이트 코드를 기계어로 변환
    - 인터프리터를 통해 한 줄씩 변환하다가 반복되는 것이 있으면 JIT컴파일러가 반복되는 코드를 한 번에 네이티브 코드로 변환. 인터프리터는 변환된 네이티브 코드를 바로 사용
  - GC에 의해 참조되지 않는 객체 제거. 힙 메모리 최적화

- JVM은 크게 4가지 컴포넌트로 구성
  - `클래스 로더 시스템` + `메모리` + `실행 엔진` + `네이티브 메소드 인터페이스 & 라이브러리` 

---

### 클래스 로더 시스템

- .class 에서 바이트코드를 읽고 메모리에 저장
- 로딩: 클래스 읽어오는 과정
- 링크: 레퍼런스를 연결하는 과정
- 초기화: static 값들 초기화 및 변수에 할당


### 메모리

- `메소드 영역`
  - 클래스 수준의 정보(클래스 이름, 부모 클래스 이름, 메소드, 변수) 저장. 공유 자원이다.
- `힙 영역`
  - 객체(인스턴스)를 저장. 공유 자원이다.
- `스택 영역`
  - 쓰레드마다 런타임 스택을 만들고, 메소드 호출을 스택 프레임이라 부르는 블럭을 쌓는다.
  - 쓰레드를 종료하면 런타임 스택도 사라진다.
- PC(Program Counter) 레지스터 
  - 쓰레드마다 쓰레드 내 현재 실행할 스택 프레임을 가리키는 포인터가 생성된다.
- 네이티브 메소드 스택
  - 네이티브 메소드 호출할 때 생기는 메서드 스택 
  - https://javapapers.com/core-java/java-jvm-run-time-data-areas/#Program_Counter_PC_Register

### 실행 엔진

- 인터프리터 
  - 바이트 코드를 한 줄씩 컴파일 해서 실행(한 줄 마다 `바이트코드 ➡️ 기계어` 변환, 실행)
  - 매번 같은 바이트 코드를 기계어로 바꾸는 것은 비효율적 
- JIT(Just-In-Time) 컴파일러
  - 인터프리터의 효율을 높이기 위해 사용
  - 인터프리터가 반복되는 메서드 호출을 발견하면 JIT 컴파일러로 반복되는 코드를 모두 네이티브 코드로 바꿔둔다. 그 다음부터 인터프리터는 네이티브 코드로 컴파일된 코드를 바로 사용한다.
    > It is used to increase the efficiency of an interpreter. It compiles the entire bytecode and changes it to native code _**so whenever the interpreter sees repeated method calls**_, JIT provides direct native code for that part so re-interpretation is not required, thus efficiency is improved.
- ⭐️ **GC(Garbage Collector)**
  - 더이상 참조되지 않는 객체를 모아서 정리한다.
  - "_가비지 컬렉터는 이해도 해야되고, 경우에 따라 옵션을 조정해야할 때도 있다._"

### JNI(Java Native Interface: 네이티브 메소드 인터페이스)

- 네이티브 메소드 사용시 동작과정 예시
  - `Thread.currentThread()`호출
  - 네이티브 메소드 스택 생성
  - 내부적으로 JNI를 호출해서 네이티브 메소드 라이브러리 사용

<br>

**※ Reference**

- [[인프런] 백기선 - 더 자바, 코드를 조작하는 다양한 방법](https://www.inflearn.com/course/lecture?courseSlug=the-java-code-manipulation&unitId=23414&tab=curriculum)
- https://www.geeksforgeeks.org/jvm-works-jvm-architecture/
- https://dzone.com/articles/jvm-architecture-explained
- http://blog.jamesdbloom.com/JVMInternals.html
