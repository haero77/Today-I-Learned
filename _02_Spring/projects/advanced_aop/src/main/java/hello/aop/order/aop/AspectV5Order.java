package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {

    /**
     * aspect 순서는 aspect 단위, 즉 클래스 단위로 지정할 수 있다.(메서드별로 @Order 적용 안된다.)
     * 순서는 @Order를 이용해서 지정. 물론, 별도의 클래스로 분리해도 되지만, 여기서는 중첩 클래스 사용
     */
    @Aspect
    @Order(2)
    public static class LogAspect {

        @Around("hello.aop.order.aop.Pointcuts.allOrder()") // 포인트컷
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            // 로깅하는 것이 이 aspect의 어드바이스.
            log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
            return joinPoint.proceed(); // 이걸 작성해야 적용된다.
        }

    }

    @Aspect
    @Order(1)
    public static class TxAspect {

        // hello.aop.order 패키지와 그 하위 패키지 이면서, 타입 이름 패턴이 *Service
        @Around("hello.aop.order.aop.Pointcuts.allOrderAndService()")
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

}
