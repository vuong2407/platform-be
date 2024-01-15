package vn.whatsenglish.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.message.PlacingOrderMessage;
import vn.whatsenglish.order.entity.Order;
import vn.whatsenglish.order.service.IOrderService;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private KafkaTemplate<Integer, PlacingOrderMessage> orderTemplate;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/test")
    public String test() {
        return OrderStatus.REJECT.toString();
    }

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody PlacingOrderMessage request) {
        Order order = orderService.placeOrder(request);
        request.setOrderId(order.getId());
        orderTemplate.send("order-requests", request.getOrderId(), request);
        System.out.println(request);
        return ResponseEntity.ok(request);
    }
}
