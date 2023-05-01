# Multi Process Vs. Multi Thread

<img src="https://velog.velcdn.com/images/balparang/post/b161fd4c-1776-4cc6-aebd-b1bbb1e75aea/image.png" width="800">

<img src="https://velog.velcdn.com/images/balparang/post/769fb599-7048-48a5-943d-36219b30d95b/image.png" width="800">

<img src="https://velog.velcdn.com/images/balparang/post/caa006ac-1fc2-4d8b-aae2-6beaab80c493/image.png" width="800">

Multi Thread는 메모리 영역을 공유하므로 Context Switching 이 수월




- 멀티 프로세스
  - 하나의 프로그램을 여러 개의 프로세스로 구성하여 각 프로세스가 1개의 작업을 처리하도록 하는 것
  - 특징
    - 1개의 프로세스가 죽어도 자식 프로세스 이외의 다른 프로세스들은 계속 실행된다.
    - Context Switching을 위한 오버헤드(캐시 초기화, 인터럽트 등)가 발생한다.
    - 프로세스는 각각 독립적인 메모리를 할당받았기 때문에 통신하는 것이 어렵다.
- 멀티 쓰레드
  - 하나의 프로그램을 여러 개의 쓰레드로 구성하여 각 쓰레드가 1개의 작업을 처리하도록 하는 것
  - 특징 
    - 프로세스를 위해 자원을 할당하는 시스템콜이나 Context Switching의 오버헤드를 줄일 수 있다. 
    - 쓰레드는 메모리를 공유하기 때문에, 통신이 쉽고 자원을 효율적으로 사용할 수 있다. 
    - 하나의 쓰레드에 문제가 생기면 전체 프로세스가 영향을 받는다. 
    - 여러 쓰레드가 하나의 자원에 동시에 접근하는 경우 자원 공유(동기화)의 문제가 발생할 수 있다.

---

- https://mangkyu.tistory.com/92