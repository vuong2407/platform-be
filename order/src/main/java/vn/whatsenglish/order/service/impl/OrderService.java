package vn.whatsenglish.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.whatsenglish.domain.dto.order.request.CreateOrderRequestDto;
import vn.whatsenglish.domain.dto.product.constants.Messages;
import vn.whatsenglish.domain.exception.NotFoundException;
import vn.whatsenglish.order.entity.Item;
import vn.whatsenglish.order.entity.Order;
import vn.whatsenglish.order.repository.ItemRepository;
import vn.whatsenglish.order.repository.OrderRepository;
import vn.whatsenglish.order.service.IOrderService;
import vn.whatsenglish.order.util.dto.ItemConverterUtil;
import vn.whatsenglish.order.util.dto.OrderConverterUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        order.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
        return order.get();
    }

    @Override
    public Order createOrder(CreateOrderRequestDto request) {
        // todo: Need to validate the info before write to DB

        List<Item> itemsRequest = request.getProductItems().stream()
                .map(ItemConverterUtil::convertInfoItemToEntity)
                .toList();
        List<Item> items = itemRepository.saveAll(itemsRequest);
        Order order = OrderConverterUtil.convertRequestCreateOrderToEntity(request);
        order.setItems(items);
        orderRepository.save(order);

        return order;
    }
}
