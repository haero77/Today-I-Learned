![조회_시_GET이_아니라_POST_(feat _414_URI_Too_Long)](https://github.com/haero77/Today-I-Learned/assets/65555299/c82e9ce9-271f-45ba-8015-9e18be4edb58)

# 들어가며

다건 조회 API를 사용하던 중, `414 URI Too Long` 상태 코드를 맞닥뜨리게 되었습니다. 
결론부터 말하자면 **HTTP 메소드를 `GET`에서 `POST`로 바꿈으로써 해결**하게 되었는데요, 문제의 원인을 찾고 해결하는 과정과, 그 속에서 동료들과 의견을 나누며 
어떤 해결책이 가장 좋은 해결책인지에 대해 같이 고민한 경험을 공유하고자 합니다. 

# 상황

(보안상 새로운 예제를 만들어 준비했습니다.)



# 원인

### 다건 조회

네. 제 예측 범위 밖이었던 거죠.
왜 `414 URI Too Long`가

말그대로 URI가 너~~~무 길다. 이 얘깁니다.

### 414 URI Too Long


# 해결 

## 시도한 방법 1: GET Body (실패)

### Spring 에서 GET Body를 지원하지 않는 건가?

### RestTemplate은 GET Body를 지원하지 않아

### 내가한 실수. 문제의 포인트를 정확히 짚지 않았다.

## 시도한 방법 2: GET -> POST (성공)



---

## 팀원들의 의견

마주한 이슈는 `POST & Request Body`를 사용함으로써 해결했습니다. <br>
이러한 해결 방식에 대해, 다른 팀원들의 의견이 듣고 싶어 조금의 용기를 내어봤습니다.  

### POST를 써도 될 것 같아.


`PATCH`, `DELETE`
팀원들과 이야기를 나눴습니다. 

### 꼭 그래야만 하는 것은 없어.


---

# 마치며



_마침._

### 더 공부해 볼 것

- HTTP Status Code 414 URI Too Long
  - https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.12 
- OpenFeign
  - RestTemplate의 유틸을 만들어서 코드 중복을 제거할지?
  - RestTemplate을 이용한 레거시가 너무 많은데 OpenFeign으로 갈 이유?

    

### 참고

- https://velog.io/@balparang/REST-API란-무엇이고-왜-사용하는-걸까

----

<br>


> _여러분들이라면 위 상황에서 어떻게 대처하실건가요?_ <br> 
> _댓글로 자유롭게 의견을 남겨주시면 감사하겠습니다._ <br>
> _부족한 글 읽어주셔서 고맙습니다._