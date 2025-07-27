# JWK와 OIDC 상관관계

## 1. 문제 상황: "이 신분증 진짜인가?" 📜

- 모바일 앱이 서버로 id_token을 전달합니다. 이 id_token은 사용자의 "디지털 신분증"과 같습니다. 
- 하지만 서버 입장에서는 이런 의심이 듭니다.
- "이 id_token이 정말 Apple이 발급한 진짜 신분증일까? 해커가 비슷하게 만들어서 보낸 가짜 신분증 아닐까?"
- 만약 서버가 가짜 신분증을 믿고 로그인시켜준다면 큰일 나겠죠.


## 2. 해결책: Apple의 "디지털 서명" ✍️

- 이 문제를 해결하기 위해 Apple은 id_token을 발급할 때 자신만의 **'디지털 서명'**을 남깁니다. 

### Apple의 역할 (서명)
  - 사용자 정보가 담긴 id_token을 만듭니다. 
  - 오직 Apple만 아는 **비밀키(Private Key)**를 사용해 id_token에 암호화된 서명을 추가합니다.
  - 즉, id_token의 헤더와 페이로드를 애플의 Private Key로 서명해서 만들어진게 id_token의 시그니처. 
  - 이 서명된 id_token을 모바일 앱에 전달합니다.

### 앱 서버의 역할 (검증)
  
- 모바일 앱으로부터 서명된 id_token을 받습니다. 
- id_token의 서명이 진짜 Apple의 서명이 맞는지 확인해야 합니다. 
- 이때, jwk-set-uri (https://appleid.apple.com/auth/keys) 주소로 찾아갑니다. 
- 그 주소에는 Apple이 미리 공개해 둔 공개키(Public Key) 목록이 있습니다. 이 공개키는 Apple의 비밀키와 한 쌍을 이룹니다. 
- 서버는 이 공개키를 가져와서 id_token에 있는 서명을 대조해 봅니다

## 3. 결론: 공개키(JWK)는 "진짜 도장 감별기" ✅

- 만약 공개키로 서명 검증에 성공하면, 서버는 두 가지 사실을 확신할 수 있습니다. 
- 진위 여부: 이 id_token은 Apple의 비밀키로 서명된 것이므로 Apple이 발급한 것이 확실하다. 
- 무결성: 중간에 누군가 내용을 수정하지 않았다. (내용이 1글자라도 바뀌면 서명 검증에 실패함)
- 만약 검증에 실패하면, 그 id_token은 가짜이거나 변조된 것이므로 즉시 폐기하고 로그인을 거부합니다.

### 서버는 Apple의 Private Key가 없는데?

```mermaid
graph TD
    subgraph "대칭키 방식 (사용자님이 생각하신 방식)"
        A["하나의 비밀키"] --> B("서명 생성");
        A --> C("서명 검증");
    end

    subgraph "비대칭키 방식 (Apple의 실제 방식)"
        D["<b>비밀키(Private Key)</b><br>Apple만 소유"] --> E("<b>서명 생성</b> (잠그기)");
        F["<b>공개키(Public Key)</b><br>모두에게 공개"] --> G("<b>서명 검증</b> (열어보기)");
    end
```

```mermaid
graph TD
    subgraph "앱 서버의 검증 로직"
        A["ID 토큰의<br>Header + Payload"] -- "1. 해시 함수 적용" --> B["<b>요약본 A</b><br>(내용물의 지문)"];

        C["ID 토큰의<br>Signature"] -- "2. <b>공개키</b>로 복호화/변환" --> D["<b>요약본 B</b><br>(서명 속의 지문)"];

        B --> E{"3. 두 요약본 비교"};
        D --> E;

        E -- "일치? ✅" --> F("<b>검증 성공</b>");
        E -- "불일치? ❌" --> G("<b>검증 실패</b>");
    end
```

```mermaid
graph TD
    subgraph "앱 서버의 상세 검증 로직"
        A["ID 토큰 수신<br>(Header.Payload.Signature)"] --> B{"1. 토큰 분리"};
        B --> C["<b>Header + Payload</b> 부분"];
        B --> D["<b>Signature</b> 부분"];

        C -- "2. SHA-256 해시 함수 적용" --> E["<b>계산된 해시값 (A)</b><br>(내용물의 실제 지문)"];

        F["JWKS에서 가져온<br><b>Apple의 공개키</b>"];

        E -- "입력 1" --> G{"<b>RS256 검증 알고리즘</b>"};
        D -- "입력 2" --> G;
        F -- "입력 3" --> G;

        G -- "세 입력을 바탕으로<br>수학적 검증 수행" --> H["최종 결과"];
        H -- "일치? ✅" --> I["<b>검증 성공</b>"];
        H -- "불일치? ❌" --> J["<b>검증 실패</b>"];
    end
```

## JWKS 인증 방식 플로우 차트

```mermaid
graph TD
    subgraph "Apple (서명 과정)"
        A["ID 토큰 원본<br>(사용자 정보)"] -- "1. Apple의 비밀키(Private Key)로 서명 ✍️" --> B["<b>서명된 ID 토큰</b><br>(잠긴 문서)"];
    end

    subgraph "앱 서버 (검증 과정)"
        F["JWK Set URI<br>(공개 열쇠 보관소)"] -- "2. 공개키(Public Key) 조회 🔑" --> G["<b>Apple의 공개키</b><br>(공개된 열쇠)"];
        B -- "앱으로 전달" --> D["앱 서버에<br><b>서명된 ID 토큰</b> 도착"];
        D -- "3. 서명 검증 시도" --> E{"<b>검증 로직</b><br>(열쇠로 잠금 해제 시도)"};
        G -- "검증에 사용" --> E;
        E --> H["성공 ✅<br>진짜 Apple이 보낸 것!<br>로그인 처리"];
        E --> I["실패 ❌<br>가짜 또는 변조된 토큰!<br>요청 거부"];
    end
```

```mermaid
sequenceDiagram
    title 시퀀스 다이어그램: ID 토큰 서명 및 JWK 검증

    participant cli as 모바일 앱
    participant server as 앱 서버
    participant apple as Apple ID 서버

    Note over apple: 1. Apple 내부에서<br>비밀키로 id_token 서명
    apple-->>cli: 서명된 id_token 발급

    cli->>server: 2. 로그인 요청 (id_token 전달)
    activate server

    Note over server: 3. id_token 검증 시작
    server->>apple: 3-1. 서명 검증용 공개키 목록(JWKS) 요청
    activate apple
    apple-->>server: 3-2. 공개키 목록 응답
    deactivate apple

    server->>server: 3-3. 전달받은 공개키로<br>id_token 서명 검증

    alt 검증 성공
        server-->>cli: 4. 로그인 성공 (자체 토큰 발급)
    else 검증 실패
        server-->>cli: 4. 로그인 실패 (에러 응답)
    end
    deactivate server
```

## 테스트를 위한 JWKs 예시

> https://www.scottbrady.io/tools/jwt

![img.png](img.png)