# REST API

<details>
    <summary><b>REST API란? RESTful 하다는게 무슨 의미인지? 1️⃣</b></summary>

### API(Application Programming Interface)

- 다른 소프트웨어 시스템과 통신하기 위해 따라야하는 규칙들을 정의한 것
  - 예를 들어 회원 리소스를 얻고 싶을 때는 어떤 URL을 써야하고, 어떤 데이터가 응답되는지 등을 정의 
- 클라이언트와 웹 리소스 사이의 게이트웨이

---

- [RESTful API란 무엇인가요? - AWS](https://aws.amazon.com/ko/what-is/restful-api/) 
- [REST API 제대로 알고 사용하기 - NHN](https://meetup.nhncloud.com/posts/92)
- [What is a REST API? - Red Hat](https://www.redhat.com/en/topics/api/what-is-a-rest-api)

</details>

<details>
    <summary><b>RESTful 규약을 다 지켜서 개발하는지? 1️⃣</b></summary>
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
    <summary><b>HTTP request 메서드 중 GET Vs. POST 비교 설명</b></summary>

> - GET은 클라이언트가 서버에게 `리소스`를 요청할 때 사용하는 메서드고, POST는 서버에게 데이터 처리(주로 생성)를 요청할 때 사용하는 메서드.
> - GET 요청의 경우 필요한 정보를 특정하기 위해 URL뒤에 Query String을 추가하여 정보를 조회하고, POST의 경우 전달할 데이터를 Body 부분에 포함하여 통신 
> - GET 요청의 경우 URL뒤의 Query String 까지 포함해서 브라우저 히스토리에 남고, 캐시가 가능하지만 POST의 경우 히스토리에 남지 않고 캐시도 불가능

- 쿼리 스트링: 물음표 뒤에 키와 밸류

</details>


<details>
    <summary><b>PUT Vs. PATCH 비교 설명</b></summary>

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
    <summary><b>401 Vs. 403?</b></summary>

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

---

# TCP/IP

<details>
    <summary><b>OSI 7계층과 TCP/IP 4계층 모델을 비교설명</b></summary>



</details>

<details>
    <summary><b>3 way handshake란?</b></summary>
</details>

---

# Authorization

<details>
    <summary><b>쿠키와 세션의 차이?</b></summary>
</details>

<details>
    <summary><b>쿠키와 세션을 이용한 로그인 방식을 화이트보드에 설명 </b></summary>
</details>