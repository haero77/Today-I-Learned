package org.festilog.springtransaction.propagation;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LogMessageRepository {

    private final EntityManager em;

//    @Transactional
    public void save(final LogMessage logMessage) {
        log.info("logMessage 저장");
        em.persist(logMessage);

        /**
         * 런타임 예외가 발생하는 상황이라고 이해하자
         */
        if (logMessage.getMessage().contains("로그예외")) {
            log.info("log 저장시 런타임 예외 발생");
            throw new RuntimeException("런타임 예외 발생"); // 런타임 예외이므로 트랜잭션이 롤백됨.
        }
    }

    public Optional<LogMessage> findByMessage(final String message) {
        return em.createQuery("select lm from LogMessage lm where lm.message = :message", LogMessage.class)
                .setParameter("message", message)
                .getResultList().stream().findAny();
    }
}
