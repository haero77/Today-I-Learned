# 문제 상황

```java
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ErrorRestControllerAdvice {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(
            final RuntimeException e,
            final HttpServletRequest httpRequest
    ) {
        log.error("handleRuntimeException", e);
        ...
    }
}
```

- 평화롭게 에러 핸들러를 위와 같이 구축하려고 했으나 다음과 같은 에러 메시지 발생.

```text
java.lang.IllegalStateException: Could not resolve parameter [1] in public org.springframework.http.ResponseEntity<org.festilog.festingserver.global.error.ApiErrorResponse> org.festilog.festingserver.global.error.ErrorRestControllerAdvice.handleRuntimeException(java.lang.RuntimeException,javax.servlet.http.HttpServletRequest): No suitable resolver
	at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues
	(InvocableHandlerMethod.java:221) ....
```

- 에러를 읽어보자면 `handleRuntimeException` 메서드에서 두 번째 파라미터인 HttpServletRequest 를 주입 못해주고 있다는 소리.  

```text
{
    "timestamp": "2024-09-23T16:01:50.646+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/hello/errors/server-error"
}
```

- 그 결과 커스텀한 에러가 아니라 스프링 기본 에러를 뿜게 된다.

# 해결

```java
import jakarta.servlet.http.HttpServletRequest;
```

- `HttpServletRequest`를 javax가 아닌 Jakarta Servlet API를 사용하도록 변경하면 해결된다.

# 원인

## 패키지 불일치

- 기존 코드는 javax.servlet.http.HttpServletRequest를 사용하고 있었음.
- 하지만 Spring Boot 3.x 환경에서는 jakarta.servlet.http.HttpServletRequest를 사용해야한다.
  - 이 불일치로 인해 Spring의 파라미터 리졸버가 적절한 객체를 찾지 못한 것이 원인.

## 클래스로더와 의존성 문제

- Java의 클래스로더는 javax.servlet.http.HttpServletRequest를 찾으려 했지만, 클래스패스에서 이를 찾을 수 없었다.
  - Spring Boot 3.x에서는 jakarta.* 패키지의 클래스만 포함하고 있기 때문.

## Spring의 파라미터 리졸빙 메커니즘

- Spring MVC는 컨트롤러 메소드의 파라미터를 분석하고 적절한 객체를 주입하는데,
- HttpServletRequest타입의 파라미터를 발견하면, 서블릿 컨테이너에서 제공하는 요청 객체를 주입하려 한다.
  - 그러나 javax.servlet.http.HttpServletRequest타입을 찾을 수 없어 "No suitable resolver" 오류가 발생했다.
