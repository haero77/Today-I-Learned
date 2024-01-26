package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import hello.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTest {

    // AspectJExpressionPointcut: 포인트컷 표현식을 처리하는 클래스.
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        // public java.lang.String hello.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod);
    }

    /**
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)throws-pattern?)
     * execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터 타입) 예외?)
     */

    @Test
    void exactMatch() {
        /**
         * 접근제어자?: public
         * 반환타입: String
         * 선언타입?: hello.aop.member.MemberServiceImpl
         * 메서드이름: hello
         * 파라미터: (String)
         * 예외?: 생략
         *
         * -> MemberServiceImpl.hello(String) 메서드와 포인트컷 표현식의 내용이 전부 일치 -> true 리턴
         */
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch() {
        /**
         * 매칭 조건
         * 접근제어자?: 생략
         * 반환타입: *
         * 선언타입?: 생략
         * 메서드이름: *
         * 파라미터: (..)
         * 예외?: 없음
         */
        /**
         * '*' 은 아무 값이 들어와도 된다는 뜻
         * 파라미터에서 '..'은 파라미터의 타입과 개수가 상관없다는 뜻.
         */
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 메서드 이름 매칭 관련 포인트컷
     * 메서드 이름 앞 뒤에 *을 사용해서 매칭할 수 있다.
     */
    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar1() {
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar2() {
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution(* nono(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

}
