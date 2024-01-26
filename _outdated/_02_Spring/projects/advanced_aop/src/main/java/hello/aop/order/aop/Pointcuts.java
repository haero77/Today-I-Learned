package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Pointcuts {

    // 타겟은 hello.aop.order 패키지와 그 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))") // pointcut experssion
    public void allOrder() { // pointcut signature. 여기서의 포인트컷 시그니처는 'allOrder()'
    }

    // 타입 이름(인터페이스, 클래스) 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {
    }

}
