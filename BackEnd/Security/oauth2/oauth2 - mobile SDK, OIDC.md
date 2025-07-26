<!-- TOC -->
* [모바일 SDK + OAuth2(OIDC) 인증 흐름](#모바일-sdk--oauth2oidc-인증-흐름)
  * [로그인(신규 유저는 회원가입)](#로그인신규-유저는-회원가입-)
  * [회원가입 시 주의점 - 유저를 (provider, email)로 식별하면 안 된다](#회원가입-시-주의점---유저를-provider-email로-식별하면-안-된다)
    * [해결 방법 - (provider, provider_user_id)로 유저를 식별](#해결-방법---provider-provider_user_id로-유저를-식별)
  * [로그인 이후 서비스 이용](#로그인-이후-서비스-이용)
    * [서버의 선제적 액세스 토큰 갱신](#서버의-선제적-액세스-토큰-갱신)
<!-- TOC -->

# 모바일 SDK + OAuth2(OIDC) 인증 흐름

## 로그인(신규 유저는 회원가입) 

```mermaid
sequenceDiagram
    title Mobile SDK + OAuth2(OIDC) + 앱 서버 인증 흐름
    autonumber
    
    participant u as 유저
    participant app as 모바일 앱
    participant svr as 앱 서버
    participant provider as OAuth2 제공자(구글, ...)
    
    %% 앱 시작 시 토큰 체크
    app->>app: 저장된 앱 액세스 토큰 확인
    
    alt 앱 액세스 토큰이 있는 경우
        app->>app: 홈 화면 표시
    else 앱 액세스 토큰이 없는 경우(=최초 로그인)
        %% 소셜 로그인 진행
        app->>app: 로그인 화면 표시
        u->>app: 소셜 로그인 버튼 클릭
        app->>provider: SDK를 통한 로그인 요청
        provider-->>u: 로그인 화면 표시
        u->>provider: 로그인 및 권한 동의
        provider-->>app: id_token, user_id 발급<br>(일반적으로 provider accessToken도 같이 발급)
        
        %% 회원가입 또는 로그인 진행
        app->>svr: (id_token, user_id) 전송<br>POST /api/v1/auth/oauth2/{provider}
        svr->>svr: id_token 검증
        svr->>svr: 사용자 존재 여부 확인<br>(provider & sub를 이용하여 유저 식별)
        
        alt 신규 유저인 경우
            svr->>provider: (필요시) user_info 조회<br>(애플의 경우 조회 불가)
            svr->>svr: 신규 유저 회원가입 진행
            svr-->>app: 토큰 발급
        else 기존 유저인 경우
            svr->>svr: 사용자 정보 조회
            svr-->>app: 로그인 완료 및 토큰 발급
        end
        
        app->>app: 액세스 토큰, 리프레시 토큰 저장
    end
```

## 회원가입 시 주의점 - 유저를 (provider, email)로 식별하면 안 된다

- 애플의 경우 최초 로그인 시에만 유저 이메일을 제공하고, 이후에는 제공하지 않음.
- 즉, 기존 유저가 존재하는 로직을 사용할 때,
    - (provider, email)로 기존 유저를 식별하려면 유저가 애플로 재 로그인할 때 문제가 된다.
        - 유저가 애플 재로그인할 때는 이메일이 없으니까 서버에서 유저 이메일을 조회할 수가 없음.

### 해결 방법 - (provider, provider_user_id)로 유저를 식별

- OIDC 프로토콜에서, `sub(앱의 provider_user_id)`는 반드시 포함된다.
- 즉, 유저가 애플로 재로그인할 때도 `sub`는 항상 존재한다.


## 로그인 이후 서비스 이용

```mermaid
sequenceDiagram
    title 액세스 토큰 만료 및 갱신 흐름
    autonumber
    
    participant app as 모바일 앱
    participant svr as 앱 서버

    %% (API 요청 전 JWT 디코딩해서 토큰 만료 여부를 미리 확인 후<br> 토큰 갱신하는 방법도 존재) 
    app->>svr: 액세스 토큰으로 API 요청
    
    alt 액세스 토큰 유효
        svr-->>app: 200 OK, API 응답
    else 액세스 토큰 만료
        svr-->>app: 401 Unauthorized
        
        app->>app: 저장된 리프레시 토큰 확인
        
        alt 리프레시 토큰 존재
            app->>svr: POST /api/v1/auth/token/refresh
            
            alt 리프레시 토큰 유효
                svr->>svr: 토큰 검증 및 새 토큰 발급
                svr-->>app: 200 OK<br>(새 액세스 토큰, 리프레시 토큰 발급)
                app->>app: 새 토큰 저장
                app->>svr: 원래 API 재요청 (with 새 액세스 토큰)
                svr-->>app: 200 OK, API 응답
            else 리프레시 토큰 만료
                svr-->>app: 401 Unauthorized
                app->>app: 저장된 토큰 모두 삭제
                app->>app: 로그인 화면으로 이동
            end
        else 리프레시 토큰 없음
            app->>app: 로그인 화면으로 이동
        end
    end
```

### 서버의 선제적 액세스 토큰 갱신

```mermaid
sequenceDiagram
    title 서버 측 선제적 액세스 토큰 갱신 흐름
    autonumber
    
    participant app as 모바일 앱
    participant svr as 앱 서버
    
    app->>svr: 액세스 토큰으로 API 요청
    
    note over svr: 서버에서 액세스 토큰의 만료 시간 확인
    svr->>svr: 토큰 만료 시간 확인
    
    alt 토큰이 곧 만료됨 (예: 5분 이내)
        svr->>svr: 사용자 ID로 저장된 리프레시 토큰 조회
        svr->>svr: 새 액세스 토큰 및 리프레시 토큰 생성
        svr-->>app: 200 OK + API 응답 + 새 토큰 (헤더 또는 응답 본문에 포함)
        note over app: 새 토큰이 응답에 포함되어 있으면 저장
        app->>app: 새 토큰 추출 및 저장
    else 토큰이 충분히 유효함
        svr-->>app: 200 OK + API 응답 (토큰 갱신 없음)
    end
```