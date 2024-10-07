package org.festilog.springtransaction.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class TxBasicTest {

    /**
     * 스프링 컨테이너가 등록된 프록시 객체를 주입해준다.
     * BasicService$$CGLIB는 BasicService를 상속해서 만들어짐
     */
    @Autowired
    BasicService basicService;

    @Test
    void proxyAppliedCheck() {
        log.info("aop class={}", basicService.getClass());
        assertThat(AopUtils.isAopProxy(basicService)).isTrue();
    }

    @TestConfiguration
    static class TxApplyBasicConfig {

        @Bean
        BasicService basicService() {
            return new BasicService();
        }
    }

    @Slf4j // static 중첩 클래스의 경우 @Slf4j가 없으면 외부 클래스를 로그로 남긴다.
    static class BasicService {

        @Transactional
        public void tx() {
            log.info("call tx()");
            final boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", isTxActive);
        }

        public void nonTx() {
            log.info("call nonTx()");
            final boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", isTxActive);
        }
    }

    @Test
    void txTest() {
        /**
         * TransactionInterceptor: Getting transaction for [org.festilog.springtransaction.apply.TxBasicTest$BasicService.tx]
         * TxBasicTest$BasicService: call tx()
         * TxBasicTest$BasicService: tx active=true
         * (이 부분에서 커밋을 친다.)
         * TransactionInterceptor: Completing transaction for [org.festilog.springtransaction.apply.TxBasicTest$BasicService.tx]
         */
        /**
         *  실제 basicService.tx() 의 호출이 끝나서 프록시로 제어가(리턴) 돌아오면,
         *  프록시는 트랜잭션 로직을 커밋하거나 롤백해서 트랜잭션을 종료한다.
         */
        basicService.tx(); // tx active=true

        /**
         * 프록시 객체의 nonTx()가 호출되긴 하지만, 트랜잭션이 적용되어있지 않기 때문에 그대로 실제 객체의 nonTx를 호출.
         */
        basicService.nonTx(); // tx active=false
    }
}
