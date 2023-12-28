package vn.whatsenglish.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.domain.dto.order.request.CreateOrderRequestDto;
import vn.whatsenglish.domain.dto.order.response.OrderResponseDto;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.message.OrderMessage;
import vn.whatsenglish.order.entity.Order;
import vn.whatsenglish.order.service.IOrderService;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private KafkaTemplate<Long, OrderMessage> template;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/test")
    public String test() {
        return OrderStatus.REJECT.toString();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDto request) {
        Order order = orderService.createOrder(request);
        OrderMessage orderMessage = OrderMessage.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .productItems(request.getProductItems())
                .totalPrice(request.getTotalPrice())
                .status(order.getOrderStatus())
                .rollbackServices(new ArrayList<>())
                .build();
        template.send("orders", orderMessage.getId(), orderMessage);
        System.out.println(orderMessage);
        return ResponseEntity.ok(order);
    }
}
