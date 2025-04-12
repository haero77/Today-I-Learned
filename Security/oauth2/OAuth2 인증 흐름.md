<!-- TOC -->
* [RFC 6749 OAuth2 Protocol Flow](#rfc-6749-oauth2-protocol-flow)
  * [OAuth2 인증 흐름](#oauth2-인증-흐름)
<!-- TOC -->

# RFC 6749 OAuth2 Protocol Flow

 > [RFC 6749 1.2 Protocol Flow](https://datatracker.ietf.org/doc/html/rfc6749#section-1.2)

```
     +--------+                               +---------------+
     |        |--(A)- Authorization Request ->|   Resource    |
     |        |                               |     Owner     |
     |        |<-(B)-- Authorization Grant ---|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(C)-- Authorization Grant -->| Authorization |
     | Client |                               |     Server    |
     |        |<-(D)----- Access Token -------|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(E)----- Access Token ------>|    Resource   |
     |        |                               |     Server    |
     |        |<-(F)--- Protected Resource ---|               |
     +--------+                               +---------------+

                     Figure 1: Abstract Protocol Flow
```

## OAuth2 인증 흐름

```mermaid
sequenceDiagram
    participant User as 사용자
    participant Server as 서버
    participant DB as 데이터베이스
    participant AuthServer as 인증 서버
    participant ResServer as 리소스 서버
    
    Note over User,ResServer: 사용자 최초 로그인
    
    User->>Server: 인증 페이지 요청 (/oauth2/authorization/{provider})
    Server->>User: 인증 서버로 리다이렉트
    
    User->>AuthServer: 인증 페이지에서 로그인
    AuthServer->>User: code 응답 후 서버로 리다이렉트 (/login/oauth2/code/{registrationId})
    
    User->>Server: 리다이렉트 (/login/oauth2/code/{registrationId}) (code 포함)
    Server->>AuthServer: 액세스 토큰 요청 (with code)
    AuthServer->>Server: 액세스 토큰 응답
    
    Server->>ResServer: 사용자 정보 요청 (with 외부 액세스 토큰)
    ResServer->>Server: 사용자 정보 응답
    
    Server->>DB: 사용자 조회 또는 신규 저장
    DB->>Server: 사용자 정보 응답
    
    Server->>Server: JWT 액세스/리프레시 토큰 생성
    Server->>User: JWT 토큰 응답 (accessToken, refreshToken)
```

---

