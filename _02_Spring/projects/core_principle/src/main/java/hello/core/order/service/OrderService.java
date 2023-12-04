package hello.core.order.service;

import hello.core.order.domain.Order;

public interface OrderService {

    Order create(Long memberId, String itemName, int itemPrice);

}
