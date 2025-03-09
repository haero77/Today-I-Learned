# 전체 플로우

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



## OAuth2 로그인 페이지 리다이렉트

### OAuth2AuthorizationRequestRedirectFilter 

```mermaid
sequenceDiagram
%% [participants]
    actor u as User Browser
    participant rf as OAuth2AuthorizationRequestRedirectFilter
    participant rr as OAuth2AuthorizationRequestResolver
    participant crr as ClientRegistrationRepository
    participant ar as AuthorizationRequestRepository

%% [start sequnce]
    u ->> rf: GET /oauth2/authorization/{registrationId}
    rf ->> rr: resolve(httpServletRequest)
    rr ->> rr: resolves registrationId from request URI
    rr ->> crr: findByRegistrationId(registrationId)
    crr -->> rr: ClientRegistration
    rr ->> rr: extract Redirect URI from ClientRegistration
    rr -->> rf: OAuth2AuthorizationRequest<br>(contains OAuth2 Redirect URI)
    rf ->> ar: saveAuthorizationRequest(authorizationRequest)
    rf -->> u: returns 302 Found with Redirect URI<br>(sendRedirect(response))
    u ->> u: Redirects to OAuth2 Authorization Page
    u ->> u: User authorizes with their account.
```

## Authorization Code 발급되고 나서 Access Token 발급 & 유저 정보 조회
 
### OAuth2LoginAuthenticationFilter

```mermaid
sequenceDiagram
    title OAuth2LoginAuthenticationFilter Flow

%% [participants]
    actor u as User Browser
    participant af as OAuth2LoginAuthenticationFilter
    participant ar as AuthorizationRequestRepository
    participant crr as ClientRegistrationRepository
    participant am as AuthenticationManager


%% [start sequnce]
    u ->> u: Redirects to APP Server<br>after authorizing OAuth2 Page
    u ->> af: GET /login/oauth2/code/{registrationId}?code={code}

%% get authorizationRequest 
    note over af, ar: Find OAuth2AuthorizationRequest <br>which already saved by OAuth2AuthorizationRequestRedirectFilter
    af ->> ar: removeAuthorizationRequest(req, res)
    ar -->> af: OAuth2AuthorizationRequest

%% get clientRegistration
    af ->> af: extract registrationId from authorizationRequest
    af ->> crr: findByRegistrationId(registartionId)
    crr -->> af: ClientRegistration
    af ->> af: generates authenticationRequest(=OAuth2LoginAuthenticationToken)<br>with clientRegistration, authorizationExchange

%% attempts authentication
    af ->> am: authenticate(authenticationRequest - OAuth2LoginAuthenticationToken)
    am -->> af: Authentication

%% process after successful authentication
    af ->> af: If authentcated, save SecurityContext
```

- 세션에 저장된 AuthorizationRequest를 찾은 후 AuthenticationManager로 인증 요청


### OAuth2LoginAuthenticationProvider 플로우

```mermaid
sequenceDiagram
    participant AuthenticationManager
    participant OAuth2LoginAuthenticationProvider
    participant OAuth2AuthorizationCodeAuthenticationProvider
    participant OAuth2AccessTokenResponseClient
    participant OAuth2UserService (DefaultOAuth2UserService)
    participant ResourceServer
    AuthenticationManager ->> OAuth2LoginAuthenticationProvider: 인증 요청 (OAuth2LoginAuthenticationToken)
    OAuth2LoginAuthenticationProvider ->> OAuth2AuthorizationCodeAuthenticationProvider: 인증 요청 (OAuth2AuthorizationCodeAuthenticationToken)
    OAuth2AuthorizationCodeAuthenticationProvider ->> OAuth2AccessTokenResponseClient: 토큰 요청 (OAuth2AuthorizationCodeGrantRequest)
    OAuth2AccessTokenResponseClient -->> OAuth2AuthorizationCodeAuthenticationProvider: OAuth2AccessTokenResponse 반환
    OAuth2AuthorizationCodeAuthenticationProvider ->> OAuth2UserService (DefaultOAuth2UserService): 사용자 정보 요청 (OAuth2UserRequest)
    OAuth2UserService (DefaultOAuth2UserService) ->> ResourceServer: 사용자 정보 요청 (GET getUserInfoUri)
    ResourceServer -->> OAuth2UserService (DefaultOAuth2UserService): 사용자 정보 응답
    OAuth2UserService (DefaultOAuth2UserService) -->> OAuth2AuthorizationCodeAuthenticationProvider: OAuth2User

```

```mermaid
sequenceDiagram
    title OAuth2LoginAuthenticationProvider authentication Flow

    %% participants
    participant am as AuthenticationManager
    participant lap as OAuth2LoginAuthenticationProvider<br>👉 Delegate autentication to codeAuthenticationProvider
    participant acap as OAuth2AuthorizationCodeAuthenticationProvider<br>👉 Request Exchanging Code To AccessToken
    participant atrc as OAuth2AccessTokenResponseClient<br>(DefaultAuthorizationCodeTokenResponseClient)
    participant us as OAuth2UserService (DefaultOAuth2UserService)

    %% start sequences
    am ->> lap: authenticate(authentication)

    %% OAuth2LoginAuthenticationProvider
    lap ->> lap: Cast authentcation to OAuth2LoginAuthenticationToken
    lap ->> lap: Genenrate 'OAuth2AuthorizationCodeAuthenticationToken'<br>contains clientRegisration & authorizationExchange
    lap ->> acap: authenticate(autentication)

    %% OAuth2AuthorizationCodeAuthenticationProvider
    acap ->> acap: Get OAuth2AuthorizationResponse<br>from authorizationExchange
    acap ->> acap: Generate 'OAuth2AuthorizationCodeGrantRequest'<br>contains clientRegistration, authorizationExchange 
    acap ->> atrc: getTokenResponse(authorizationGrantRequest)

    %% OAuth2AccessTokenResponseClient
    note over atrc, atrc: 🚀 Exchange Authorization Code to AccessToken<br>via call Authorization Server endpoint.
    atrc ->> atrc: Convert authorizationCodeGrantRequest to RequestEntity
    atrc ->> atrc: Exchange request to OAuth2AccessTokenResponse<br>via call Authorization Server endpoint.
    atrc -->> acap: returns tokenResponse    

    acap ->> acap: Generate authenticated OAuth2AuthorizationCodeAuthenticationToken
    acap -->> lap: returns OAuth2AuthorizationCodeAuthenticationToken

    %% request load user
    lap ->> lap: Extract AccessToken from OAuth2AuthorizationCodeAuthenticationToken<br>Generate OAuth2UserRequest with AccessToken
    lap ->> us: loadUser(oauth2UserRequest)

    %% UserService
    note over us, us: 🚀 Exchange AccessToken to UserInfo<br>via call Resource Server
    us ->> us: Validate UserInfoEndPoints Exists.
    us ->> us: Validate UserNameAttributeName Exists.
    us ->> us: Convert userRequest to RequestEntity<br>& Get UserAttributes via call Resource Server

    note over us, us: 👨‍💻 Devleper will implement Member Sign up process<br>using CustomOAuth2UserService if needed.

    us ->> lap: returns DefaultOAuth2SUser

    %% End of OAuth2 authentication
    lap -->> am: Authentication
    note over am, am: OAuth2LoginAuthenticationFilter saves SecurityContext<br><End of OAuth2 Authetication>
```

