# Process


### Process

- **_실행파일(program)이 memory(RAM)에 적재되어 CPU에 의해 실행되는 것을 `process` 라고 한다._**
    - (+ program은 단순히 명령어 리스트를 포함하는 파일이다)

![](https://velog.velcdn.com/images/balparang/post/a1eb07cf-01f5-4e34-9964-8488e57fa115/image.png)

- 프로그램은 하드에 저장되고, CPU는 하드디스크를 읽을 수 없다. CPU는 RAM 메모리에 올라와있는 프로그램만 읽을 수 있다.

### Process 메모리 영역

![](https://velog.velcdn.com/images/balparang/post/2d8ed7a1-dbc1-4e5b-a2ee-772b7d1d611c/image.png)

<img src="https://velog.velcdn.com/images/balparang/post/e1158821-fda8-4bb6-b3a5-7ba02987838e/image.png" width="500">

- memory는 CPU가 직접 접근할 수 있는 컴퓨터 내부의 기억장치이다. program이 CPU에서 실행되려면 해당 내용이 memory에 적재된 상태여야만 한다.
- 프로세스에 할당되는 memory 공간은 Code, Data, Stack, Heap 4개의 영역으로 이루어져 있으며 각 process 마다 독립적으로 할당 받는다.(각 process마다 Code, Data, Stack, Heap을 갖는다.)


| 영역    | 설명                                          |
|-------|---------------------------------------------|
| Code  | 실행한 프로그램의 코드가 저장되는 메모리 영역                   |
| Data  | 프로그램의 전역 변수와 static 변수가 저장되는 메모리 영역         |
| Heap  | 프로그래머가 직접 공간을 할당(malloc)/해제(free) 하는 메모리 영역 |
| Stack | 함수 호출 시 생성되는 지역 변수와 매개 변수가 저장되는 임시 메모리 영역   |


### PC Register에는 CPU가 어떤 코드를 읽어야할지 저장되어있다. 

- 프로그램의 코드를 토대로 CPU가 실제로 연산을 해야만 프로그램이 실행된다.
- CPU가 어떤 코드를 읽어야하는가는 `PC(Program Counter) Register`에 저장되어있다. 
  - **_PC Register에는 다음에 실행될 코드(명령어, instruction)의 주소값이 저장되어있다._**
  - 해당 명령어를 읽어와서 CPU가 연산을 하게 되면 process 가 실행되는 것이다.


# Multi Process

### Multi Process 

- Multi Process란 2개 이상의 process가 동시에 실행되는 것을 말한다.
- 동시에라는 말은 동시성(Concurrency)과 병렬성(Parallelism) 두 가지를 의미한다.
- `동시성`
  - CPU Core가 1개일 때, 여러 process를 짧은 시간동안 번갈아가면서 연산을 하게 되는 시분할 시스템(Time Sharing System)으로 실행되는 것
- `병렬성`
  - CPU Core가 여러개일 때, 각각의 Core가 각각의 process를 연산함으로써 process가 동시에 실행되는 것

하나의 CPU는 매 순간 하나의 process 만 연산할 수 있다. 그러나 CPU의 처리 속도가 매우 빨라서 짧은 시간동안 여러 프로세스가 CPU에서 번갈아 실행되기 때문에 사용자 입장에서는 동시에 실행되는 것 처럼 보인다. 이렇게 CPU의 작업시간을 여러 process 조금씩 나누어 쓰는 시스템을 시분할 시스템(Time Sharing System) 이라고 한다.


| 동시성               | 병렬성                  |
|-------------------|----------------------|
| Single Core       | Multi Core           |
| 동시에 실행되는 것처럼 보인다. | 실제로 동시에 여러 작업이 처리된다. |


> _면접에서는 병렬성보다 동시성이 훨씬 중요하다._

## Context, PCB, Context Switch

### Context 

> _"context란 process가 현재 어떤 상태로 수행되고 있는지에 대한 정보이다. 해당 정보는 PCB에 저장한다."_

- 시분할 시스템에서는 한 프로세스가 매우 짧은 시간동안 CPU를 점유하여 일정부분의 명령을 수행하고, 다른 프로세스에게 넘긴다. 
- 따라서, 이전에 어디까지 명령을 수행했고, register에는 어떤 값이 저장되어 있었는지에 대한 정보가 필요하다.
- **_process가 현재 어떤 상태로 수행되고 있는지에 대한 총체적인 정보가 바로 context이다._** 
- context 정보는 `PCB`에 저장한다.


### PCB(Process Control Block)

- **_PCB는 운영체제가 프로세스를 표현한 자료구조이다._** 
- **_PCB에는 프로세스의 중요한 정보가 포함되어있기 때문에, 일반 사용자가 접근하지 못하도록 보호된 메모리 영역에 저장 된다._**
  - 일부 운영 체제에서 PCB는 커널(=운영체제) 스택에 위치한다.
- 다음과 같은 정보를 포함
  - Process State: new, running, waiting, halted 등의 state가 있다.
    - `실행(running)`: 프로세스가 CPU를 점유하고 명령을 수행 중인 상태
    - `준비(ready`): CPU만 할당받으면 즉시 명령을 수행할 수 있도록 준비된 상태
    - `봉쇄(wait, sleep, blocked)`: CPU를 할당받아도 명령을 실행할 수 없는 상태. ex) I/O 작업을 기다리는 경우 등 
  - Process Number: 해당 프로세스의 number
  - Program Counter: 해당 프로세스가 다음에 실행할 명령어의 주소를 가리킨다.
  - Registers
  - 메모리 정보: 해당 process의 주소 공간 등
  - Memory limits
  - ...


<img src="https://velog.velcdn.com/images/balparang/post/467d942f-dc5c-4614-a4b2-03d883669dae/image.png" width="800">

- 프로세스의 메타 정보를 담는 context가 PCB에 저장된다.

<img src="https://velog.velcdn.com/images/balparang/post/07263f74-2b38-482c-aae1-e082d4a9364f/image.png" width="800">

- PCB는 커널 스택에 저장된다.
  - ~~운영체제도 하나의 프로세스이고, 커널 스택 메모리를 할당 받는다.~~

### Context Switch

- **_한 프로세스에서 다른 프로세스로 CPU 제어권을 넘겨주는 것_**
- 이 때 이전의 프로세스 상태를 **_PCB에 저장하여 보관_** 하고, 새로운 프로세스의 ***PCB를 읽어서 보관된 상태를 복구***하는 작업이 이루어진다.
- 실행 중인 프로세스에 `인터럽트`가 발생하면 운영체제가 다른 프로세스를 실행 상태로 바구고 제어를 넘겨주어 Context Switching이 일어난다.


<img src="https://velog.velcdn.com/images/balparang/post/a0dd4159-1ad1-42df-afbd-81336521c0fa/image.png" width="500">


## 기타 

### 메모리 관리

- 여러 process가 동시에 memory에 적재된 경우, 서로 다른 process의 영역을 침범하지 않도록 각 process가 자신의 memory 영역에만 접근하도록 운영체제가 관리해준다.
  - (참고: base register, limit register를 통해 각 process를 처리할 때에는 정해진 메모리 구간 외에는 접근할 수 없도록 막는다.)


