package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    // 타겟은 hello.aop.order 패키지와 그 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))") // pointcut experssion
    private void allOrder() { // pointcut signature. 여기서의 포인트컷 시그니처는 'allOrder()'
    }

    // 타입 이름(인터페이스, 클래스) 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {
    }

    @Around("allOrder()") // 포인트컷
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 로깅하는 것이 이 aspect의 어드바이스.
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed(); // 이걸 작성해야 적용된다.
    }

    // hello.aop.order 패키지와 그 하위 패키지 이면서, 타입 이름 패턴이 *Service
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();// 어드바이스 적용
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

}
