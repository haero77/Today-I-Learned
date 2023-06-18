# CORS(Cross Origin Resource Sharing)

👉 **_서로 다른 도메인에서의 HTTP 통신을 가능케하는 정책_**  

- 서버 내에서 요청이 허락된 도메인에만 데이터를 주기 위해서 만들어짐.
- 브라우저는 기본적으로 `SOP`(Same Origin Policy)
  - 보안을 위해 같은 도메인만 통신하도록 허용.
  - 웹이 복잡해지면서 서로 다른 출처의 리소스를 사용하는 일이 잦아짐. 
    - SOP의 예외 조항인 `CORS`를 만들게 된다.

## 해결 방법

- 응답 헤더의 `Access-Control-Allow-Origin` 요청을 보내는 URL을 포함시킨다. 

### Spring에서의 CORS 세팅 

- `@CrossOrigin`을 이용
- `WebMvcConfigurer`의 `addCorsMappings()`를 오버라이딩
  - 허용 메서드 등을 지정 가능