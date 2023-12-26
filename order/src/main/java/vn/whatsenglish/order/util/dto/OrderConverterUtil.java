package vn.whatsenglish.order.util.dto;

import vn.whatsenglish.domain.dto.order.request.CreateOrderRequestDto;
import vn.whatsenglish.order.entity.Order;

public class OrderConverterUtil {
    public static Order convertRequestCreateOrderToEntity(CreateOrderRequestDto dto) {
        return Order.builder()
                .orderCode(dto.getOrderCode())
                .customerId(dto.getCustomerId())
                .shippingAddressId(dto.getShippingAddressId())
                .orderStatus(dto.getOrderStatus())
                .build();
    }
}
