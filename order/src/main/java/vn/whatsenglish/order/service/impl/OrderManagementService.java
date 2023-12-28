package vn.whatsenglish.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.message.OrderMessage;
import vn.whatsenglish.order.entity.Order;
import vn.whatsenglish.order.repository.OrderRepository;
import vn.whatsenglish.order.service.IOrderService;

@Service
public class OrderManagementService {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    public OrderMessage confirm(OrderMessage orderPayment, OrderMessage orderProduct) {
        Order order = orderService.getOrderById(orderPayment.getId());
        orderPayment.getRollbackServices().addAll(orderProduct.getRollbackServices());
        if (orderPayment.getStatus().equals(OrderStatus.ACCEPT.toString())
                && orderProduct.getStatus().equals(OrderStatus.ACCEPT.toString())) {
            order.setOrderStatus(OrderStatus.CONFIRMED.toString());
        } else if (orderPayment.getStatus().equals(OrderStatus.REJECT.toString())
                && orderProduct.getStatus().equals(OrderStatus.REJECT.toString())) {
            order.setOrderStatus(OrderStatus.REJECTED.toString());
        } else if (orderPayment.getStatus().equals(OrderStatus.REJECT.toString())
                || orderProduct.getStatus().equals(OrderStatus.REJECT.toString())) {
            order.setOrderStatus(OrderStatus.ROLLBACK.toString());
        }
        orderRepository.save(order);
        orderPayment.setStatus(order.getOrderStatus());
        return orderPayment;
    }
}
