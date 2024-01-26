# Thread


# Multi Thread

### Stack Memory & PC Register

- Thread가 함수를 호출하기 위해서는 인자 전달, Return Address 저장, 함수 내 지역변수 저장등을 위한 독립적인 stack memory 공간이 필요하다.
  - 결과적으로 thread는 process로부터 stack memory 영역을 독립적으로 할당받고, Code, Data, Heap 영역은 공유하는 형태를 갖게 된다. 
- Multi Thread에서는 각각의 thread마다 PC Register를 갖고 있어야한다.
  - 한 process 내에서도 thread끼리 context switch가 일어나게 되는데, PC Register에 code address 가 저장되어 있어야 실행을 할 수 있기 때문이다.

