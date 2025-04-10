<!-- TOC -->
* [RFC 6749 OAuth2 Protocol Flow](#rfc-6749-oauth2-protocol-flow)
  * [OAuth2 인증 흐름](#oauth2-인증-흐름)
* [OIDC(OpenID Connect)](#oidcopenid-connect-)
  * [실생활 비유](#실생활-비유)
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

# OIDC(OpenID Connect) 

- OIDC(OpenID Connect)?
  - 사용자 인증을 위한 개방형 표준 프로토콜.
  - 간단히 말하자면, 사용자가 누구인지 확인하는 방법을 표준화한 것
- 정의:
  - OpenID Connect는 OAuth 2.0 프로토콜 위에 구축된 인증 레이어
  쉽게 말해, "이 사람이 누구인지" 확인할 수 있는 표준화된 방법
- 목적:
  - 사용자 인증(Authentication): "이 사람이 본인이 맞다"는 것을 증명
  - OAuth 2.0은 권한 부여(Authorization)에 중점을 두는 반면, OIDC는 인증에 중점을 둠
- 핵심 요소:
  - ID 토큰: JWT(JSON Web Token) 형식의 토큰으로, 사용자의 신원 정보 포함 
  - UserInfo 엔드포인트: 사용자에 대한 추가 정보를 얻을 수 있는 API

## 실생활 비유

- OIDC는 마치 여권이나 신분증과 같음. 
  - 신뢰할 수 있는 기관(예: 카카오)이 발급 
  - 신분증에는 본인 확인에 필요한 정보가 포함됨 
  - 제3자(내 서비스)가 신분증을 확인해 본인 여부를 판단


```mermaid
sequenceDiagram
    participant user as 사용자(여행자)
    participant server as 앱 서버(입국심사대)
    participant kakao as 카카오(신분증 발급국)
    
    Note over kakao: 신분증 발급국은 특별한 도장과<br/>위조 방지 기술을 가지고 있음
    
    Note over server,kakao: 사전 준비 단계 (가끔 수행)
    server->>kakao: 도장 진위 확인 방법(공개키) 요청
    kakao->>server: 도장 확인 방법 제공(공개키 목록)
    server ->> server: 도장 확인 방법 저장(공개키 목록)
    
    Note over user,kakao: 실제 인증 과정 (사용자마다 수행)
    user->>kakao: 신분증 발급 요청(로그인)
    kakao->>kakao: 신원 확인
    kakao-->>user: 디지털 서명된 신분증(id_token) 발급
    
    user->>server: 신분증 제시(id_token 전달)
    
    Note over server: 신분증(id_token) 검증
    server ->> server: 1. 신분증 발급 국가 확인(iss)
    server ->> server: 2. 유효기간 확인(exp)
    server ->> server: 3. 신분증에 찍힌 도장(서명) 검증
    
    alt 신분증 유효함
        server->>user: 입국 허가(로그인 성공)
    else 신분증 유효하지 않음
        server->>user: 입국 거부(로그인 실패)
    end
```

## 모바일 SDK를 이용한 OIDC 로그인 
