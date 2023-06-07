# TCP/IP

<details>
    <summary><b>✅ OSI 7계층과 TCP/IP 4계층 모델을 비교설명</b></summary>

- OSI 7계층은 네트워크 통신을 표준화한 모델
- OSI 모델이 실무적으로 사용하기에는 복잡하기 때문에, 실제 인터넷은 이를 단순화한 TCP/IP 4계층을 사용

<img width="778" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/da5cce3e-07c3-4ce9-a43f-d48ed7ce6c2f">

- OSI 7계층과 TCP/IP 4계층 모델에서 각 계층은 하위 계층의 기능을 이용하고, 상위 계층에게 기능을 제공 
  - 예) 응용 계층의 HTTP 프로토콜은 전송 계층의 TCP, 네트워크 계층의 IP를 이용
- 일반적으로 상위 계층은 소프트웨어로, 하위 계층은 하드 웨어로 구성.
  - 예) 물리 계층의 통신은 케이블을 이용한 전기 신호로 이루어짐.
  
</details>

<details>
    <summary><b>✅ 캡슐화, 역캡슐화</b></summary>

- 캡슐화: 전송하고자 하는 데이터에, 각 프로토콜의 정보를 헤더에 포함시켜서 하위 계층에 전달하는 것.
- 역캡슐화: 상대측에서 헤더를 역순으로 제거해가며 상위 계층으로 데이터를 전달하는 것. 최종 적으로 원본 데이터 수신 

<img width="1044" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/6ca24708-8299-4f04-9b37-db60e9fe983c">


</details>

<details>
    <summary><b>✅ TCP Vs. UDP? ⭐️</b></summary>

- TCP
  - 연결형, 신뢰성 전송 프로토콜
  - 연결지향적 서비스를 하기 위해 데이터 전송 전 `3way handshaking`을 통해 두 호스트 사이에 논리적인 연결을 만든다.
  - 신뢰성 있는 서비스를 제공하기 위해 오류 제어, 흐름 제어, 혼잡 제어 등을 실행
  - 신뢰성을 보장하기 위해 헤더가 더 크고 속도가 비교적 느리다. 
  - 신뢰성이 중요한 통신인 HTTP, 파일 전송 등에 쓰인다.
- UDP
  - 비연결형 프로토콜 👉 `3way handshaking` 등 세션 수립과정이 없음
  - 빈신뢰성 프로토콜 👉 오류 제어, 흐름 제어, 혼잡 제어 제공하지 않음
  - 단순성 덕분에 적은양의 오버헤드를 갖고, 수신 여부를 확인하지 않아 속도가 더 빠르다.
  - 실시간성이 중요한 통신인 동영상 스트리밍 등에 쓰인다.
  
---

### TCP/IP

- 인터넷에서 사용되는 프로토콜 그룹을 `TCP/IP` 라고 부른다.
  - 4계층 또는 5계층으로 나뉨
    - application layer
    - transport
    - network(internet)
    - data link, physical 
- 전송 계층은 두 응용 계층 사이에서 `process-to-process` 통신을 제공
- 전송 계층은 응용 계층에서 보낸 메시지를 받아 전송 계층으로 캡슐화하여 하위 계층에 전송
  - TCP로 전송하는 패킷을 `segment`라고 한다.
  - UDP로 전송하는 패킷을 `datagram`이라고 한다. 

> 💡 패킷 = 헤더 부분 + 데이터(payload) 부분

<img width="1005" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/99914dd5-40f8-4be9-94d5-886f4a631f71">

### TCP(Transmission Control Protocol)

- 연결형, 신뢰성 프로토콜
- 연결형
  - 연결지향적 서비스를 제공하기 위해, 데이터 전송 전 `3way handshaking`을 통해 세션 수립
  - 그 후 데이터를 전송하고, 데이터 전송이 끝나면 연결을 끊는다. 
  - TCP 통신은 이렇게 `connection setup` -> `data transfer` -> `connection termination` 세 단계로 나뉨
- 신뢰성 
  - 신뢰성 있는 서비스를 제공하기 위해 TCP가 **전체 스트림을 순서에 맞게, 오류 없이, 부분적 손실이나 중복 없이 전송하는 것을 보장.**
    - 오류 제어, 흐름 제어, 혼잡 제어가 이를 가능케한다.
  - 흐름 제어 
    - 데이터를 보내는 속도와 데이터를 받는 속도의 균형을 맞추는 것
  - 오류 제어
    - 훼손된 segment의 감지 및 재전송, 손실된 segment의 재전송
    - TCP헤더의 checksum, 확인-응답, 타임아웃 등을 통해 수행됨

<img width="1052" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/2221311a-d1ed-4071-9080-0d8378ac6918">

### UDP

- 비연결성, 비신뢰성 프로토콜
- 비연결성
  - 논리적 연결을 성립하지 않고, datagram을 보낸다.
- 비신뢰성
  - 흐름 제어, 오류 제어, 혼잡 제어를 제공하지 않음
  - 패킷이 손실되어도 상관 없음
- 파일을 다운로드 하는 경우, 파일이 훼손되면 안 되므로 TCP
- 실시간 방송인 경우 한 프레임 정도 사라져도 인식을 못하므로 UDP

<img width="1005" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/a94e774a-2f00-4da4-851b-3bdacbfea371">

- 세션 수립 과정 없다.
- 패킷 순서 보장하지 않음

### UDP(User Datagram Protocol)

</details>

<details>
    <summary><b>✅ 3 way handshake란? 그 과정은?</b></summary>

> - 3-way handshake란 TCP/IP 프로토콜로 통신하기 전 정확한 데이터 전송을 위해 상대방 호스트와 세션을 수립하는 과정.(=TCP 연결 초기화)
> - 과정
>   1. 클라이언트가 서버에게 접속을 요청하는 `SYN(싱크)` 패킷을 보냄
>   2. 서버는 요청을 수락하는 ACK 패킷을 포함하여 `SYN + ACK` 패킷을 클라이언트로 전송
>   3. 클라이언트가 이것을 수신한 후, 다시 `ACK` 패킷을 서버에 발송하면 연결이 이루어지고, 이로써 데이터를 주고받을 수 있게 됨.
> 
---  

- `HTTP 1.1`과 `2.0` 버전 모두 TCP 프로토콜을 이용 
  - 👉 우리가 네이버에 접속할 때마다 네이버의 서버와 나의 서버가 3 way handshake를 하게 된다.

### 3-way handshaking

<img width="773" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/b8305312-12b1-4ff9-9659-00d0e914c24f">

TCP 통신은 3단계 과정을 거친다.

1. Connection Setup (TCP 연결 초기화) 👉 `3-way handshaking` 이용
   - 우리가 공부한 `3-way handshaking`이 Connection Setup 과정인 것 
2. Data Transfer (데이터 전송)
3. Connection Termination (TCP 연결 종료) 👉 `4-way handshaking` 이용

</details>

<details>
    <summary><b>🔼 4 way handshaking?</b></summary>

> - TCP 연결 시 3 way handshaking을 이용하여 Connection Setup을 하고,  
> - Connection Termination시에는 4 way handshaking을 이용.

---

### 4-way handshaking

<img width="859" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/063a08c7-3b02-4f7d-bf83-37acc1b2714d">

`TCP Connection Termination`은 양방향으로 2개의 연결이 독립적으로 닫히기 때문에 `4-way handshaking`을 사용한다.


</details>


---

# HTTP

<details>
    <summary><b>HTTP란?</b></summary>

> - `HyperText Transfer Protocol`의 약자로, 클라이언트-서버 모델을 따르면서 request-response 구조로 웹 상에서 정보를 주고받을 수 있는 프로토콜
> - TCP/IP 기반으로 동작하며, 가장 큰 특징은 `Connectionless`와 `Stateless`

- 웹상에서 정보를 전송하기 위한 프로토콜로써, HTML과 같은 문서를 전송하는 것에 사용
- 클라이언트가 HTTP request를 서버에 보내면 서버는 HTTP response를 클라이언트에 보내는 구조

### Request, Response message 구조

<img width="991" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/7a3cef85-befa-487a-9c18-0804bb46e9bd">

- request message
  - start line(method, path, HTTP version)
  - headers
    - host, accept-language 등
  - body
- response message
  - status line(HTTP version, status code, status message)
  - headers
    - 날짜, 서버 정보 등
  - body


### Connectionless, Stateless

- `Connectionless`
  - 서버에 연결 후 요청에 응답을 받으면 연결을 끊어버리는 특성
  - 이 특성으로 인해 많은 사람이 웹을 사용하더라도 실제 동시 접속을 최소화 👉 더 많은 요청을 처리 가능  
- `Stateless`
  - Connectionsless로 연결을 끊었기 때문에, 클라이언트의 이전 상태(로그인 유무 등)을 알 수 없는 특성
  - 정보를 유지할 수 없는 Connectionless, Stateless 특성을 가진 HTTP의 단점을 보완하고자 `Cookie`, `Session`, `JWT`등이 도입됨

</details>

<details>
    <summary><b>HTTP Vs. HTTPS?</b></summary>

- HTTP는 정보를 text형태로 주고받기 때문에, 중간에 인터셉트될 경우 데이터 유출될 수 있음
- HTTP에 암호화를 추가한 프로토콜이 `HTTPS`

</details>

<details>
    <summary><b>HTTP 버전별 차이?</b></summary>
</details>

<details>
    <summary><b>✅ HTTP request 메서드 중 GET Vs. POST 비교 설명 ⭐️⭐️</b></summary>

> - GET은 클라이언트가 서버에게 `리소스`를 요청할 때 사용하는 메서드고, POST는 서버에게 데이터 처리(주로 생성)를 요청할 때 사용하는 메서드.
> - GET 요청의 경우 필요한 정보를 특정하기 위해 URL뒤에 Query String을 추가하여 정보를 조회하고, POST의 경우 전달할 데이터를 Body 부분에 포함하여 통신 
> - GET 요청의 경우 URL뒤의 Query String 까지 포함해서 브라우저 히스토리에 남고, 캐시가 가능하지만 POST의 경우 히스토리에 남지 않고 캐시도 불가능

- 쿼리 스트링: 물음표 뒤에 키와 밸류

</details>


<details>
    <summary><b>PUT Vs. PATCH 비교 설명, 멱등성?</b></summary>

> - PUT과 PATCH 메소드 모두 서버의 리소스를 업데이트하는 메소드
> - PUT의 경우 모든 리소스를 수정, 대체하고 PATCH 경우 리소스 일부만 수정

- PUT: 리소스를 아예 대체한다. 이 때 해당 리소스가 없으면 생성
- PATCH: 리소스의 일부분을 수정

</details>

<details>
    <summary><b>HTTP status code를 설명해주세요.</b></summary>

> - 클라이언트가 보낸 HTTP 요청에 대한 서버의 응답 코드
> - 상태 코드를 통해 요청의 성공/실패 여부를 판단할 수 있다.
> - 100번대부터 500번대까지 총 5개의 클래스로 구분되어 HTTP 요청에 대한 상태를 알려준다

### Status Code

- 1xx (정보)
  - 요청을 받았으며 작업을 계속한다.
- 2xx (성공)
  - 클라이언특 요청한 동작을 성공적으로 수신하여 이해했고, 성공적으로 처리했다. 
- 3xx (리다이렉션)
  - 요청을 완료하기 위해 추가 작업 조치가 필요하다.
- 4xx (클라이언트 오류)
  - 클라이언트의 요청에 문제가 있다.
- 5xx (서버 오류)
  - 서버가 유효한 요청의 수행을 실패했다.

### 자주 등장하는 HTTP 응답 코드

- 200 OK
  - 요청이 성공함
  - 예) 잔액 조회 성공
- 201 Created
  - 리소스 생성 성공
  - 예) 게시글 작성 성공, 회원가입 성공
- 400 Bad Request
  - 데이터의 형식이 올바르지 않는 등 서버가 요청을 이해할 수 없음
  - 예) 올바르지 않은 형식의 데이터 입력
- 401 Unauthorized
  - **인증되지 않은 상태에서 인증이 필요한 리소스에 접근**
  - 예) 로그인 전에 사용자 정보 요청
- 403 Forbidden
  - **인증된 상태에서 권한이 없는 리소스에 접근**
  - 예) 일반 유저가 관리자 메뉴 접근 등
- 404 Not Found
  - 요청한 route가 없음. 찾는 리소스가 없음
  - 예) 존재하지 않는 URL(route)에 요청 
- 500 Bad Gateway 
  - 서버에서 예상하지 못한 에러 발생
  - 예) 예외처리를 하지 않은 오류가 발생

</details>

<details>
    <summary><b>✅ 401 Vs. 403?</b></summary>

> - 두 상태코드 모두 400번대로 클라이언트의 요청에 문제가 있음을 나타냄
> - `401`은 인증이 되지 않은 상태에서, 인증이 필요한 리소스에 접근함 
>   - 예) 로그인 전에 사용자 정보 요청
> - `403`은 인증은 되었으나, 권한이 없는 페이지에 접근
>   - 예) 일반 유저가 관리자 메뉴에 접근하는 경우

</details>

<details>
    <summary><b>인증 Vs. 인가?</b></summary>
</details>

<details>
    <summary><b>www.google.com을 주소창에 입력했을 때 나오는 과정?</b></summary>

> 1. 사용자가 브라우저에 URL을 입력
> 2. 브라우저는 DNS를 통해 서버의 IP주소를 찾는다.
> 3. client에서 HTTP request 메시지 생성 ➡️ TCP/IP 패킷 생성 ➡️ 서버로 전송
> 4. 서버에서 HTTP request에 대한 HTTP response 메시지 생성 ➡️ TCP/IP 패킷 생성 ➡️ 클라이언트로 전송
> 5. 도착한 HTTP response message는 웹 브라우저에 의해 출력(렌더링)

<img width="1026" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/2550e81c-3ee4-4e6e-8a1e-bd1a94760e9f">

1. 유저가 브라우저에서 www.google.com(URL)을 입력을 하면 HTTP request message를 생성합니다.
2. IP주소를 알아야 전송을 할 수 있으므로, DNS lookup을 통해 해당 domain의 server IP주소를 알아냅니다.
3. 반환된 IP주소(구글의 server IP)로 HTTP 요청 메시지(request message) 전송 요청을 합니다.
   1. 생성된 HTTP 요청 메시지를 TCP/IP층에 전달합니다.
   2. HTTP 요청 메시지에 헤더를 추가해서 TCP/IP 패킷을 생성합니다.
4. 해당 패킷은 전기신호로 랜선을 통해 네트워크로 전송되고, 목적지 IP에 도달합니다.
5. 구글 server에 도착한 패킷은 unpacking을 통해 message를 복원하고 server의 process로 보냅니다.
6. server의 process는 HTTP 요청 메시지에 대한 response data를 가지고 HTTP 응답 메시지(response message)를 생성 합니다.
7. HTTP 응답 메시지를 전달 받은 방식 그대로 client IP로 전송을 합니다.
8. HTTP response 메시지에 담긴 데이터를 토대로 웹브라우저에서 HTML 렌더링을 하여 모니터에 검색창이 보여집니다.

</details>

<details>
    <summary><b>도메인, URI Vs. URL?</b></summary>
</details>

---



---

# Authorization

<details>
    <summary><b>쿠키와 세션의 차이?</b></summary>
</details>

<details>
    <summary><b>쿠키와 세션을 이용한 로그인 방식을 화이트보드에 설명 </b></summary>
</details>

---

# REST API

<details>
    <summary><b>✅ REST API란? RESTful 하다는게 무슨 의미인지? 1️⃣</b></summary>

> - REST API는 REST 아키텍처 스타일을 따르는 API.
    >   - URI를 이용하여 사용할 자원을 지정하고, HTTP 메서드를 이용하여 자원의 접근 방식을 지정함으로써 요청 형식만으로 어떤 요청인지 추론이 가능 👉 협업을 가능케함
> - REST란 HTTP URI를 이용하여 사용할 자원을 지정하고, HTTP 메서드를 이용하여 자원의 접근 방식을 지정하는 소프트웨어 아키텍쳐
> - REST 원칙을 전부 지키기는 어려우므로 RESTful API를 사용.
> - RESTful API를 사용하여 구현한 웹 애플리케이션을 RESTful 웹 서비스라고 함.

### API(Application Programming Interface)

- 소프트웨어가 다른 소프트웨어로부터 지정된 형식으로 요청, 명령을 받을 수 있는 수단
- 다른 소프트웨어 시스템과 통신하기 위해 따라야하는 규칙들을 정의한 것
  - 예를 들어 회원 리소스를 얻고 싶을 때는 어떤 URL을 써야하고, 어떤 데이터가 응답되는지 등을 정의
- 클라이언트와 웹 리소스 사이의 게이트웨이


---

- [RESTful API란 ? - 이동규(씨유)님](https://brainbackdoor.tistory.com/53)
- [[Network] REST란? REST API란? RESTful이란?](https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html)
- [RESTful API란 무엇인가요? - AWS](https://aws.amazon.com/ko/what-is/restful-api/)
- [REST API 제대로 알고 사용하기 - NHN](https://meetup.nhncloud.com/posts/92)
- [What is a REST API? - Red Hat](https://www.redhat.com/en/topics/api/what-is-a-rest-api)
- [REST API 란 무엇입니까? - IBM](https://www.youtube.com/watch?v=lsMQRaeKNDk)
- [REST API가 뭔가요? - 얄코](https://www.youtube.com/watch?v=iOueE9AXDQQ)
- [면접 단골 질문! API, REST API가 뭔가요? (개발 필수지식)](https://www.youtube.com/watch?v=C7yhysF_wAg)

- [[10분 테코톡] 정의 REST API](https://www.youtube.com/watch?v=Nxi8Ur89Akw)
- [It is okay to use POST - Roy T. Fielding(REST 창시자)](https://roy.gbiv.com/untangled/2009/it-is-okay-to-use-post)

</details>

<details>
    <summary><b>✅ RESTful 규약을 다 지켜서 개발하는지? 1️⃣</b></summary>

> - 그렇지 않다.
> - Uniform Interface `HATEOS` 등을
> - 따라서 엄밀한 의미이 REST의 장점을 계승하는 RESTful API를 사
>

- HATEOS: 하이퍼링크를 통해 리소스의 상태를 전이할 수 있어야함
  - 예: Location 등에 접근 가능한 URI를 제공.

</details>

<details>
    <summary><b>Redirect Vs. Forward</b></summary>
</details>

<details>
    <summary><b>Redirect, Forward 어떻게 구현하는지?</b></summary>
</details>


<details>
    <summary><b>(플젝) HTTP Referer 가 뭔지?</b></summary>
</details>

<details>
    <summary><b>(플젝) HTTP Referer를 어떻게 식별하는가?</b></summary>
</details>

---

# 캐시

<details>
    <summary><b>캐시란?</b></summary>

- 웹 캐시는 자주 쓰이는 문서의 사본을 자동으로 보관하는 HTTP 장치
- 웹 요청이 캐시에 도착했을 때, 캐시된 로컬 사본이 존재한다면, 그 문서는 원 서버가 아니라 그 캐시로부터 제공
- 장점
  - 불필요한 데이터 전송을 줄여서, 네트워크 요금으로 인한 비용을 줄여 준다.
  - 원 서버에 대한 요청을 줄여준다. 서버는 부하를 줄일 수 있으며 더 빨리 응답할 수 있게 된다.
- 단점
  - 

---

- https://brainbackdoor.tistory.com/53 

</details>

<details>
    <summary><b>브라우저 캐시란?</b></summary>
</details>


---

# CORS

<details>
    <summary><b>CORS란?</b></summary>
</details>

<details>
    <summary><b>CORS 해결 방법이란?</b></summary>
</details>