package org.festilog.springtransaction.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void complete() throws NotEnoughMoneyException {
        // given
        final Order order = new Order();
        order.setUsername("정상");

        // when
        orderService.order(order);

        // then
        final Order findOrder = orderRepository.findById(order.getId()).get();
        assertThat(findOrder.getPayStatus()).isEqualTo("완료");
    }

    @Test
    void runtimeException() {
        //given
        Order order = new Order();
        order.setUsername("예외");

        //when, then
        assertThatThrownBy(() -> orderService.order(order)) // 트랜잭션이 롤백되므로 INSERT 쿼리 날릴 필요가 없어진다.
                .isInstanceOf(RuntimeException.class);

        //then: 롤백되었으므로 데이터가 없어야 한다.
        Optional<Order> orderOptional = orderRepository.findById(order.getId());
        assertThat(orderOptional.isEmpty()).isTrue();
    }

    /**
     * o.f.s.order.OrderService: 결제 프로세스 진입
     * o.f.s.order.OrderService: 잔고 부족 비즈니스 예외 발생
     * o.s.t.i.TransactionInterceptor: Completing transaction for [org.festilog.springtransaction.order.OrderService.order] after exception: org.festilog.springtransaction.order.NotEnoughMoneyException: 잔고가 부족합니다.
     * o.s.orm.jpa.JpaTransactionManager: Initiating transaction commit
     * o.s.orm.jpa.JpaTransactionManager: Committing JPA transaction on EntityManager [SessionImpl(1868532012<open>)]
     * org.hibernate.SQL: insert into orders (pay_status,username,id) values (?,?,?)
     * org.hibernate.SQL: update orders set pay_status=?,username=? where id=? 👈 쿼리가 나가고 나서 '진짜' DB쿼리가 발생
     */
    @Test
    void bizException() {
        //given
        Order order = new Order();
        order.setUsername("잔고부족");

        //when
        try {
            orderService.order(order);
             // fail("잔고 부족 예외가 발생해야 합니다.");
        } catch (NotEnoughMoneyException e) {
            log.info("고객에게 잔고 부족을 알리고 별도의 계좌로 입금하도록 안내");
        }

        // then
        Order findOrder = orderRepository.findById(order.getId()).get();
        assertThat(findOrder.getPayStatus()).isEqualTo("대기");
    }
}
