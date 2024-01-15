package vn.whatsenglish.orchestrator.service.impl;

import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.whatsenglish.domain.message.PlacingOrderMessageResponse;
import vn.whatsenglish.orchestrator.entity.OrderEvent;
import vn.whatsenglish.orchestrator.repository.OrderEventRepository;
import vn.whatsenglish.orchestrator.service.IOrderEvent;

@Service
@AllArgsConstructor
public class OrderEventService implements IOrderEvent {

    private OrderEventRepository orderEventRepository;

    @Override
    public OrderEvent createOrderEvent(
            PlacingOrderMessageResponse placingOrderMessageResponse) {
        OrderEvent orderEvent = OrderEvent.builder()
                .orderId(placingOrderMessageResponse.getOrderId())
                .orderStatus(placingOrderMessageResponse.getOrderStatus().toString())
                .build();
        return orderEventRepository.save(orderEvent);
    }
}
