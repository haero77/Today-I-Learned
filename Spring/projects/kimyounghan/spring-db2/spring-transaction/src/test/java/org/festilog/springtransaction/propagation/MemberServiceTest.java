package org.festilog.springtransaction.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
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
     *
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
}
