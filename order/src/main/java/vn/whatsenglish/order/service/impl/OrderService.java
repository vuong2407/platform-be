package vn.whatsenglish.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.whatsenglish.domain.dto.order.request.CreateOrderRequestDto;
import vn.whatsenglish.domain.dto.product.constants.Messages;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.exception.NotFoundException;
import vn.whatsenglish.domain.message.PlacingOrderMessage;
import vn.whatsenglish.domain.message.PlacingOrderMessageResponse;
import vn.whatsenglish.order.entity.Item;
import vn.whatsenglish.order.entity.Order;
import vn.whatsenglish.order.repository.ItemRepository;
import vn.whatsenglish.order.repository.OrderRepository;
import vn.whatsenglish.order.service.IOrderService;
import vn.whatsenglish.order.util.dto.ItemConverterUtil;
import vn.whatsenglish.order.util.dto.OrderConverterUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        order.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
        return order.get();
    }

    @Override
    public Order createOrder(CreateOrderRequestDto request) {
        // todo: Need to validate the info before write to DB

//        List<Item> itemsRequest = request.getProductItems().stream()
//                .map(ItemConverterUtil::convertInfoItemToEntity)
//                .toList();
//        List<Item> items = itemRepository.saveAll(itemsRequest);
//        Order order = OrderConverterUtil.convertRequestCreateOrderToEntity(request);
//        order.setItems(items);
//        orderRepository.save(order);

        return null;
    }

    @Override
    public Order placeOrder(PlacingOrderMessage request) {
        List<Item> itemsRequest = request.getItems().stream()
                .map(ItemConverterUtil::convertInfoItemToEntity)
                .toList();
        List<Item> items = itemRepository.saveAll(itemsRequest);
        Order order = Order.builder()
                .orderCode(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.NEW.toString())
                .customerId(request.getUserId())
                .items(items)
                .build();
        orderRepository.save(order);
        return order;
    }

    @Override
    public void updateStatusOrder(Integer orderId, String status) {
        Order order = getOrderById(orderId);
        order.setOrderStatus(status);
        orderRepository.save(order);
    }
}
