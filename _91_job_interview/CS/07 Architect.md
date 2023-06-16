# 아키텍쳐

<details>
    <summary><b>Layered Architecture?</b></summary>

- Spring MVC에서 많이 사용되는 아키텍쳐
- **왜 레이어를 분리하는가? 👉 관심사의 분리**
- 장점
  - 높은 유지보수성
  - 쉬운 테스트 

### Layers

- Presentation Layer
  - 사용자가 데이터를 전달하기 위해 화면에 정보를 표시하는 것을 주 관심사로 둔다.
  - Presentation Layer 는 비즈니스 로직이 어떻게 수행되는지 알 필요가 없다. 
  - ex) View, Controller 
- Business Layer
  - Persistence Layer에서 데이터를 가져와 비즈니스 로직을 수행 
  - ex) Service, Domain
- Persistence Layer
  - 애플리케이션의 영속성을 구현하기 위해 데이터 소스로부터 데이터를 가져오는 역할 
  - ex) Repository, DAO

<img width="999" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/1e0bbd22-f74f-44e9-b204-17dfebe75b7d">




</details>


<br>

---

# 대용량 설계 

<details>
    <summary><b>대용량 트래픽 처리를 어떻게 할까?</b></summary>
</details>

<details>
    <summary><b>대용량 데이터 처리를 어떻게 할까?</b></summary>
</details>

<details>
    <summary><b>메시지 큐에 대해 설명해주세요.</b></summary>

- [ 메세지 큐(Message Queue)란? ]
  - 메세지 큐(Message Queue)란 Queue 자료구조를 이용하여 데이터(메세지)를 관리하는 시스템으로, 비동기 통신 프로토콜을 제공하여 메세지를 빠르게 주고 받을 수 있게 해준다. 메세지 큐에서는 Producer(생산자)가 Message를 Queue에 넣어두면, Consumer가 Message를 가져와 처리하게 된다. 메세지 큐에는 Kafka, Rabbit MQ, AMPQ 등이 있다.

---

- https://mangkyu.tistory.com/88

</details>

<br>

---