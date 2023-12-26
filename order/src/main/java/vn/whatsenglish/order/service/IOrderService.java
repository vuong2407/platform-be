package vn.whatsenglish.order.service;

import vn.whatsenglish.domain.dto.order.request.CreateOrderRequestDto;
import vn.whatsenglish.order.entity.Order;

public interface IOrderService {
    Order createOrder(CreateOrderRequestDto request);
}
