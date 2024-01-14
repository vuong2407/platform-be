package vn.whatsenglish.order.service;

import vn.whatsenglish.domain.dto.order.request.CreateOrderRequestDto;
import vn.whatsenglish.domain.message.PlacingOrderMessage;
import vn.whatsenglish.domain.message.PlacingOrderMessageResponse;
import vn.whatsenglish.order.entity.Order;

public interface IOrderService {
    Order getOrderById(Integer id);
    Order createOrder(CreateOrderRequestDto request);
    Order placeOrder(PlacingOrderMessage request);
    void updateStatusOrder(Integer orderId, String status);
}
