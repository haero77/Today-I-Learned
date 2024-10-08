package org.festilog.springtransaction.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LogMessageRepository logMessageRepository;

    /**
     * memberService         @Transactional: OFF
     * memberRepository      @Transactional: ON
     * logMessageRepository  @Transactional: ON
     */
    /**
     * o.f.s.propagation.MemberService          : == memberRepository 호출 시작 ==
     * o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [org.festilog.springtransaction.propagation.MemberRepository.save]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1900694434<open>)] for JPA transaction
     * o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@6d71f296]
     * o.s.t.i.TransactionInterceptor           : Getting transaction for [org.festilog.springtransaction.propagation.MemberRepository.save]
     * o.f.s.propagation.MemberRepository       : member 저장
     * org.hibernate.SQL                        : select next value for member_seq
     * o.s.t.i.TransactionInterceptor           : Completing transaction for [org.festilog.springtransaction.propagation.MemberRepository.save]
     * o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit 👈 스프링 트랜잭션이 끝난 후
     * o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1900694434<open>)]
     * org.hibernate.SQL                        : insert into member (username,id) values (?,?) 👈 플러시해서 영속성 컨텍스트 변경 사항 반영 & DB 트랜잭션 커밋
     * o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager [SessionImpl(1900694434<open>)] after transaction
     * o.f.s.propagation.MemberService          : == memberRepository 호출 종료 ==
     * <p>
     * o.f.s.propagation.MemberService          : == logMessageRepository 호출 시작 ==
     * o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [org.festilog.springtransaction.propagation.LogMessageRepository.save]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(304060411<open>)] for JPA transaction
     * o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@70a3b]
     * o.s.t.i.TransactionInterceptor           : Getting transaction for [org.festilog.springtransaction.propagation.LogMessageRepository.save]
     * o.f.s.propagation.LogMessageRepository   : logMessage 저장
     * org.hibernate.SQL                        : select next value for log_message_seq
     * o.s.t.i.TransactionInterceptor           : Completing transaction for [org.festilog.springtransaction.propagation.LogMessageRepository.save]
     * o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
     * o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(304060411<open>)]
     * org.hibernate.SQL                        : insert into log_message (message,id) values (?,?)
     * o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager [SessionImpl(304060411<open>)] after transaction
     * o.f.s.propagation.MemberService          : == logMessageRepository 호출 종료 ==
     */
    @Test
    void outerTxOff_success() {
        // given
        final String username = "outerTxOff_success";

        // when
        memberService.joinV1(username);

        // then
        assertAll(
                () -> assertThat(memberRepository.findByUsername(username)).isNotEmpty(),
                () -> assertThat(logMessageRepository.findByMessage(username)).isNotEmpty()
        );
    }

    /**
     * memberService         @Transactional: OFF
     * memberRepository      @Transactional: ON
     * logMessageRepository  @Transactional: ON Exception
     */
    /**
     * o.f.s.propagation.MemberService          : == memberRepository 호출 시작 ==
     * o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [org.festilog.springtransaction.propagation.MemberRepository.save]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(794042208<open>)] for JPA transaction
     * o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@69542112]
     * o.s.t.i.TransactionInterceptor           : Getting transaction for [org.festilog.springtransaction.propagation.MemberRepository.save]
     * o.f.s.propagation.MemberRepository       : member 저장
     * org.hibernate.SQL                        : select next value for member_seq
     * o.s.t.i.TransactionInterceptor           : Completing transaction for [org.festilog.springtransaction.propagation.MemberRepository.save]
     * o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
     * o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(794042208<open>)]
     * org.hibernate.SQL                        : insert into member (username,id) values (?,?)
     * o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager [SessionImpl(794042208<open>)] after transaction
     * o.f.s.propagation.MemberService          : == memberRepository 호출 종료 ==
     * <p>
     * o.f.s.propagation.MemberService          : == logMessageRepository 호출 시작 ==
     * o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [org.festilog.springtransaction.propagation.LogMessageRepository.save]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1281995670<open>)] for JPA transaction
     * o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@49314684]
     * o.s.t.i.TransactionInterceptor           : Getting transaction for [org.festilog.springtransaction.propagation.LogMessageRepository.save]
     * o.f.s.propagation.LogMessageRepository   : logMessage 저장
     * org.hibernate.SQL                        : select next value for log_message_seq
     * o.f.s.propagation.LogMessageRepository   : log 저장시 런타임 예외 발생 👈 트랜잭션 시작 후 실제 객체 로직에서 예외가 발생해서, 프록시 객체로 예외가 올라온다. 프록시 객체는 런타임 예외이므로 롤백을 수행하게 된다.
     * o.s.t.i.TransactionInterceptor           : Completing transaction for [org.festilog.springtransaction.propagation.LogMessageRepository.save] after exception: java.lang.RuntimeException: 런타임 예외 발생
     * o.s.orm.jpa.JpaTransactionManager        : Initiating transaction rollback 👈 런타임 예외 발생으로 인해 AOP Proxy 객체에서 트랜잭션 매니저에게 해당 트랜잭션 롤백 요청했음.
     * o.s.orm.jpa.JpaTransactionManager        : Rolling back JPA transaction on EntityManager [SessionImpl(1281995670<open>)]
     * o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager [SessionImpl(1281995670<open>)] after transaction
     */
    @Test
    void outerTxOff_rollback() {
        // given
        final String username = "로그예외";

        // when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        // then
        assertThat(memberRepository.findByUsername(username)).isNotEmpty(); // 회원 저장 트랜잭션은 커밋되었으므로 회원 존재.
        assertThat(logMessageRepository.findByMessage(username)).isEmpty(); // 로그 저장 트랜잭션은 롤백되었으므로 로그 부재.
    }

    /**
     * 단일 트랜잭션
     */
    /**
     * memberService         @Transactional: ON
     * memberRepository      @Transactional: OFF
     * logMessageRepository  @Transactional: OFF
     */
    /**
     * o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [org.festilog.springtransaction.propagation.MemberService.joinV1]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1333633954<open>)] for JPA transaction
     * o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@110f66e3]
     * o.s.t.i.TransactionInterceptor           : Getting transaction for [org.festilog.springtransaction.propagation.MemberService.joinV1]
     * <p>
     * o.f.s.propagation.MemberService          : == memberRepository 호출 시작 ==
     * o.f.s.propagation.MemberRepository       : member 저장
     * org.hibernate.SQL                        : select next value for member_seq
     * o.f.s.propagation.MemberService          : == memberRepository 호출 종료 ==
     * <p>
     * o.f.s.propagation.MemberService          : == logMessageRepository 호출 시작 ==
     * o.f.s.propagation.LogMessageRepository   : logMessage 저장
     * org.hibernate.SQL                        : select next value for log_message_seq
     * o.f.s.propagation.MemberService          : == logMessageRepository 호출 종료 ==
     * <p>
     * o.s.t.i.TransactionInterceptor           : Completing transaction for [org.festilog.springtransaction.propagation.MemberService.joinV1]
     * o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
     * o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1333633954<open>)]
     * org.hibernate.SQL                        : insert into member (username,id) values (?,?)
     * org.hibernate.SQL                        : insert into log_message (message,id) values (?,?)
     * o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager [SessionImpl(1333633954<open>)] after transaction
     */
    @Test
    void singleTx() {
        // given
        final String username = "singleTx";

        // when
        memberService.joinV1(username);

        // then
        assertThat(memberRepository.findByUsername(username)).isNotEmpty();
        assertThat(logMessageRepository.findByMessage(username)).isNotEmpty();
    }
}
