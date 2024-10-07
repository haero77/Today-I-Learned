package org.festilog.springtransaction.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
public class RollbackTest {

    @Autowired
    RollbackService service;

    @TestConfiguration
    static class RollbackTestConfig {

        @Bean
        RollbackService rollbackService() {
            return new RollbackService();
        }
    }

    @Slf4j
    static class RollbackService {

        // 런타임 예외 발생: 롤백
        @Transactional
        public void runtimeException() {
            log.info("call runtimeException");
            throw new RuntimeException();
        }

        // 체크 예외 발생: 커밋
        @Transactional
        public void checkedException() throws MyException {
            log.info("call checkedException");
            throw new MyException();
        }

        // 체크 예외 rollbackFor 지정: 롤백
        @Transactional(rollbackFor = MyException.class) // 체크 예외는 원래 커밋하지만, MyException에 대해서는 롤백할 것이다.
        public void rollbackFor() throws MyException {
            log.info("call rollbackFor");
            throw new MyException();
        }
    }

    // CheckedException
    static class MyException extends Exception {

    }

    @Test
    void runtimeException() {
        /**
         * o.s.orm.jpa.JpaTransactionManager: Creating new transaction with name [org.festilog.springtransaction.exception.RollbackTest$RollbackService.runtimeException]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
         * o.s.orm.jpa.JpaTransactionManager: Opened new EntityManager [SessionImpl(197690005<open>)] for JPA transaction
         * o.s.orm.jpa.JpaTransactionManager: Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@5e557671]
         * o.s.t.i.TransactionInterceptor: Getting transaction for [org.festilog.springtransaction.exception.RollbackTest$RollbackService.runtimeException]
         * o.f.s.e.RollbackTest$RollbackService: call runtimeException
         * o.s.t.i.TransactionInterceptor: Completing transaction for [org.festilog.springtransaction.exception.RollbackTest$RollbackService.runtimeException] after exception: java.lang.RuntimeException
         * o.s.orm.jpa.JpaTransactionManager: Initiating transaction rollback 👈 런타임 예외 발생 & 롤백 수행.
         * o.s.orm.jpa.JpaTransactionManager: Rolling back JPA transaction on EntityManager [SessionImpl(197690005<open>)]
         * o.s.orm.jpa.JpaTransactionManager: Closing JPA EntityManager [SessionImpl(197690005<open>)] after transaction
         */
        assertThatThrownBy(() -> service.runtimeException())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void checkedException() {
        /**
         * o.s.orm.jpa.JpaTransactionManager: Creating new transaction with name [org.festilog.springtransaction.exception.RollbackTest$RollbackService.checkedException]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
         * o.s.orm.jpa.JpaTransactionManager: Opened new EntityManager [SessionImpl(2067156807<open>)] for JPA transaction
         * o.s.orm.jpa.JpaTransactionManager: Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@5c8d631]
         * o.s.t.i.TransactionInterceptor: Getting transaction for [org.festilog.springtransaction.exception.RollbackTest$RollbackService.checkedException]
         * o.f.s.e.RollbackTest$RollbackService: call checkedException
         * o.s.t.i.TransactionInterceptor: Completing transaction for [org.festilog.springtransaction.exception.RollbackTest$RollbackService.checkedException] after exception: org.festilog.springtransaction.exception.RollbackTest$MyException
         * o.s.orm.jpa.JpaTransactionManager: Initiating transaction commit 👈 체크 예외는 롤백이 아니라 커밋!!!
         * o.s.orm.jpa.JpaTransactionManager: Committing JPA transaction on EntityManager [SessionImpl(2067156807<open>)]
         * o.s.orm.jpa.JpaTransactionManager: Closing JPA EntityManager [SessionImpl(2067156807<open>)] after transaction
         */
        assertThatThrownBy(() -> service.checkedException())
                .isInstanceOf(MyException.class);
    }

    @Test
    void rollbackFor() {
        /**
         * o.s.orm.jpa.JpaTransactionManager: Creating new transaction with name [org.festilog.springtransaction.exception.RollbackTest$RollbackService.rollbackFor]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT,-org.festilog.springtransaction.exception.RollbackTest$MyException
         * o.s.orm.jpa.JpaTransactionManager: Opened new EntityManager [SessionImpl(1187280314<open>)] for JPA transaction
         * o.s.orm.jpa.JpaTransactionManager: Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@68cb8e52]
         * o.s.t.i.TransactionInterceptor: Getting transaction for [org.festilog.springtransaction.exception.RollbackTest$RollbackService.rollbackFor]
         * o.f.s.e.RollbackTest$RollbackService: call rollbackFor
         * o.s.t.i.TransactionInterceptor: Completing transaction for [org.festilog.springtransaction.exception.RollbackTest$RollbackService.rollbackFor] after exception: org.festilog.springtransaction.exception.RollbackTest$MyException
         * o.s.orm.jpa.JpaTransactionManager: Initiating transaction rollback 👈 체크 예외지만 rollbackFor를 썼으므로 롤백.
         * o.s.orm.jpa.JpaTransactionManager: Rolling back JPA transaction on EntityManager [SessionImpl(1187280314<open>)]
         * o.s.orm.jpa.JpaTransactionManager: Closing JPA EntityManager [SessionImpl(1187280314<open>)] after transaction
         */
        assertThatThrownBy(() -> service.rollbackFor())
                .isInstanceOf(MyException.class);
    }
}
