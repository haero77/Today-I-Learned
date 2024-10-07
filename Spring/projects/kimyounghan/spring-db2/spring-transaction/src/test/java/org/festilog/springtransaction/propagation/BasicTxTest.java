package org.festilog.springtransaction.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class BasicTxTest {

    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class Config {

        /**
         * 원래는 스프링 부트가 트랜잭션 매니저도 자동으로 등록해주는데,
         * 이렇게 직접 빈을 등록하면 그 대신 이것을 사용하게 된다.
         */
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@918922423 wrapping conn0: url=jdbc:h2:mem:7acbda96-0ea2-4d0a-a6be-420f047a52cb user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@918922423 wrapping conn0: url=jdbc:h2:mem:7acbda96-0ea2-4d0a-a6be-420f047a52cb user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 커밋 시작
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@918922423 wrapping conn0: url=jdbc:h2:mem:7acbda96-0ea2-4d0a-a6be-420f047a52cb user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@918922423 wrapping conn0: url=jdbc:h2:mem:7acbda96-0ea2-4d0a-a6be-420f047a52cb user=SA] after transaction
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 커밋 완료
     */
    @Test
    void commit() {
        log.info("트랜잭션 시작");
        final TransactionStatus transactionStatus = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 커밋 시작");
        txManager.commit(transactionStatus);
        log.info("트랜잭션 커밋 완료");
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@117556243 wrapping conn0: url=jdbc:h2:mem:933cf4b3-b3ef-48e4-87e8-c8bae8492ca1 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@117556243 wrapping conn0: url=jdbc:h2:mem:933cf4b3-b3ef-48e4-87e8-c8bae8492ca1 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 롤백 시작
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction rollback
     * o.s.j.d.DataSourceTransactionManager     : Rolling back JDBC transaction on Connection [HikariProxyConnection@117556243 wrapping conn0: url=jdbc:h2:mem:933cf4b3-b3ef-48e4-87e8-c8bae8492ca1 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@117556243 wrapping conn0: url=jdbc:h2:mem:933cf4b3-b3ef-48e4-87e8-c8bae8492ca1 user=SA] after transaction
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 롤백 완료
     */
    @Test
    void rollback() {
        log.info("트랜잭션 시작");
        final TransactionStatus transactionStatus = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 롤백 시작");
        txManager.rollback(transactionStatus);
        log.info("트랜잭션 롤백 완료");
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 트랜잭션1 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * <p>
     * 👉 'conn0' 커넥션 획득
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1114712230 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1114712230 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션1 커밋
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@1114712230 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA]
     * <p>
     * 👉 'conn0' 커넥션 반납
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1114712230 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] after transaction
     * <p>
     * <p>
     * <p>
     * o.f.s.propagation.BasicTxTest            : 트랜잭션2 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * <p>
     * 👉 'conn0' 커넥션 획득(HikariProxyConnection@12345678 처럼 커넥션은 다르지만, 실제로는 같은 물리 커넥션인 'conn0'을 사용.
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@940656203 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@940656203 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션2 커밋
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@940656203 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA]
     * <p>
     * 👉 'conn0' 커넥션 반납
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@940656203 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] after transaction
     */
    @Test
    void double_commit() {
        log.info("트랜잭션1 시작");
        final TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션2 시작");
        final TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 커밋");
        txManager.commit(tx2);
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 트랜잭션1 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1612491156 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1612491156 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션1 커밋
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@1612491156 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1612491156 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] after transaction
     *
     * o.f.s.propagation.BasicTxTest            : 트랜잭션2 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1770893302 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1770893302 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션2 롤백
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction rollback
     * o.s.j.d.DataSourceTransactionManager     : Rolling back JDBC transaction on Connection [HikariProxyConnection@1770893302 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1770893302 wrapping conn0:
     */
    @Test
    void double_commit_rollback() { // 👉 트랜잭션 2개가 각각 수행
        log.info("트랜잭션1 시작");
        final TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션2 시작");
        final TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 롤백");
        txManager.rollback(tx2);
    }
}
