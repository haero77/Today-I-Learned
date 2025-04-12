<!-- TOC -->
* [OIDC(OpenID Connect)](#oidcopenid-connect)
  * [실생활 비유](#실생활-비유)
  * [모바일 SDK를 이용한 OIDC 로그인](#모바일-sdk를-이용한-oidc-로그인)
    * [간략 버전](#간략-버전)
<!-- TOC -->

> - [OpenId.net - How OpenID Connect Works](https://openid.net/developers/how-connect-works/)
> - [Microsoft - what-is-openid-connect-oidc](https://www.microsoft.com/ko-kr/security/business/security-101/what-is-openid-connect-oidc)

# OIDC(OpenID Connect)

> 디지털 리소스에 액세스하기 위해 로그인할 때 사용자 ID를 확인하는 인증 프로토콜.

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
    title OIDC 로그인 과정 (추상화)
    
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

### 간략 버전

```mermaid
sequenceDiagram
    title OIDC 로그인 과정
    
    participant user as 유저
    participant cli as 모바일 앱(SDK)
    participant server as 앱 서버
    participant kakao as 카카오
    
    Note over user,kakao: 로그인 과정
    user->>cli: 카카오 로그인 요청 <br> (서버 개발자가 전달한 네이티브 앱 키 이용)
    cli->>kakao: SDK를 통한 인증 요청
    kakao-->user: 로그인 화면 표시
    user->>kakao: 카카오 계정으로 로그인
    kakao-->>user: 앱 권한 동의 화면 표시
    user->>kakao: 권한 동의
    
    kakao-->>cli: id_token 및 access_token 발급
    
    cli->>server: id_token과 user_id 전송 (API 호출)
    
    Note over server: id_token 검증 
    
    alt 기존 사용자인 경우
        server->>server: 사용자 정보 로드
        server->>server: JWT 토큰 생성 (액세스/리프레시)
        server->>cli: 로그인 성공 응답 (자체 토큰 포함)
    else 신규 사용자인 경우
        server->>kakao: 사용자 추가 정보 요청 (선택적)
        kakao->>server: 이메일, 닉네임 등 정보 응답
        server->>server: 새 사용자 DB에 저장
        server->>server: JWT 토큰 생성 (액세스/리프레시)
        server->>cli: 회원가입 및 로그인 성공 응답 (자체 토큰 포함)
    end
    
    cli->>cli: 토큰 저장
    cli->>user: 로그인 완료 화면 표시
```