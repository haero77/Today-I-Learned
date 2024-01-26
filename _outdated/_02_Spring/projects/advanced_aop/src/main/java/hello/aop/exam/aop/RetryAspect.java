package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

    @Around(value = "@annotation(retry)")
    public Object deRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable { // 어드바이스에 애노테이션을 파라미터로 전달.

        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) { // maxRetry 개수까지만 catch가 일어난다.
                exceptionHolder = e;
            }
        }

        throw exceptionHolder;
    }

}
