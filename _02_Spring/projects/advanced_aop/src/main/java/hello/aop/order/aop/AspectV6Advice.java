package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // @Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();// 어드바이스 적용

            // @AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            // @After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    /**
     * 조인 포인트 실행 전.
     * Around는 ProceedingJoinPoint.proceed() 를 호출해야 다음 대상을 호출하지만,
     * 나머지 어드바이스(ex. @Before) 같은 경우는 메서드 종료 시
     * 자동으로 다음 타겟 호출
     */
    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }

    /**
     * 메서드 실행이 정상적으로 반환될 때 실행.
     * returning 속성에 사용된 이름은 어드바이스 메서드의 매개변수 이름과 일치해야함.
     * returning 절에 '지정된 타입의 값을 반환하는 메서드만' 대상으로 실행.
     * (부모 타입 지정 시 모든 자식 타입 인정) Around와 다르게 반환되는 객체 변경은 불가. 반환 객체를 조작은 가능.
     */
    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    /**
     * 메서드 실행이 예외를 던져서 종료될 때 실행
     * throwing 속성에 사용된 이름은 어드바이스 메서드의 매개변수 이름과 일치
     * throwing 절에 지정된 타입(또는 하위 타입)과 맞는 예외를 대상으로 실행
     */
    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", joinPoint.getSignature(), ex.getMessage());
    }

    /**
     * 메서드 실행이 종료되면 실행(finally 와 비슷.)
     * 정상 및 예외 반환 조건을 모두 처리한다.
     * 일반적으로 리소스를 해제하는 데 사용함.
     */
    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }

}
