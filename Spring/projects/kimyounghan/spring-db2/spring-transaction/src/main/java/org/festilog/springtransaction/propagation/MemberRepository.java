package org.festilog.springtransaction.propagation;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

//    @Transactional // JPA의 모든 데이터 변경은 트랜잭션 안에서 이루어져야한다.
    public void save(final Member member) {
        log.info("member 저장");
        em.persist(member);
    }

    public Optional<Member> findByUsername(final String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList().stream().findAny();
    }
}
