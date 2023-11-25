package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

    // 타겟은 hello.aop.order 패키지와 그 하위 패키지
    @Around("execution(* hello.aop.order..*(..))") // 포인트컷
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 로깅하는 것이 이 aspect의 어드바이스.
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed(); // 이걸 작성해야 적용된다.
    }

}
