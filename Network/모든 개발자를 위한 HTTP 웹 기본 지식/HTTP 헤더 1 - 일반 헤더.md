# HTTP 헤더 개요

# 표현

# 콘텐츠 협상

# 전송 방식

# 일반 정보

# 특별한 정보

# 인증

- `Authorization`: 클라이언트 인증 정보를 서버에 전달
- `WWW-Authenticate`: 리소스 접근 시 필요한 인증 방법 정의

## Authorization

> 클라이언트 인증 정보를 서버에 전달

- Authorization: Basic xxxxxxxxxx
  - 인증을 공부하다보면, Authorization에 어떤 값을 넣어야할 지 감을 잡게 될 것.


## WWW-Authenticate

- 리소스 접근 시 필요한 인증 방법 정의
- 401 Unauthorized 응답과 함께 사용
- 401 오류가 날 때 아래 헤더를 같이 넣어줘야한다.
  -  WWW-Authenticate: Newauth realm="apps", type=1,
     title="Login to \"apps\"", Basic realm="simple"

---

# 쿠키
