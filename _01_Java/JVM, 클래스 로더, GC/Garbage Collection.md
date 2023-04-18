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