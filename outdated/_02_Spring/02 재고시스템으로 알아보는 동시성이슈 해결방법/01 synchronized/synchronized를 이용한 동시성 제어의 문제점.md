# `synchronized`를 이용한 동시성 제어의 문제

- Java의 `synchronized` 의 경우 한 프로세스 안에서만 보장된다.
  - 각 스레드 사용으로 인한 갱신 손실 문제는 해결

### 서버가 여러대라면?

<img width="800" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/ec4f114b-d6b8-48b2-acea-889fd9cc0dc8">

- A 서버의 `synchonized`가 B서버의 동시성을 제어할 수는 없음.

<br>

<img width="800" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/b81b8562-16a6-4760-a693-bb30ee696388">

- `synchronized`는 각 프로세스 안에서만 보장이 되기 때문에, 
  - 여러 서버를 쓰는 경우 결국 여러 스레드가 동시에 데이터에 접근이 가능해진다.
  - 👉 `갱신 손실` 문제 여전히 가능
  

- **실제 운영중인 서버는 대부분 2대 이상을 쓰기 때문에, `synchonized`는 거의 사용되지 않는다.**

👉 MySQL이 지원하는 방법으로 RaceCondition을 해결하는 방법을 알아보자