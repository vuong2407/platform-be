package vn.whatsenglish.product.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.message.OrderMessage;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderManagementService {

    private static final String SERVICE = "product";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<Long, OrderMessage> template;

    public void reserve(OrderMessage orderMessage) {
        boolean isValid = true;
        Map<Long, Integer> map = new HashMap<>();
        orderMessage.getProductItems().forEach(item -> map.put(item.getProductId(), item.getQuantity()));
        List<Product> products = productRepository.findAllById(map.keySet());
        System.out.println("product reserve");
        for (Product product : products) {
            if (product.getAvailableItem() < map.get(product.getId())) {
                isValid = false;
                break;
            }
        }
        if (orderMessage.getStatus().equals(OrderStatus.NEW.toString())) {
            if (isValid) {
                orderMessage.setStatus(OrderStatus.ACCEPT.toString());
                List<Product> newProducts = products.stream().map(product -> {
                    product.setReservedItem(product.getReservedItem() + map.get(product.getId()));
                    product.setAvailableItem(product.getAvailableItem() - map.get(product.getId()));
                    return product;
                }).toList();
                productRepository.saveAll(newProducts);
                orderMessage.getRollbackServices().add(SERVICE);
            } else {
                orderMessage.setStatus(OrderStatus.REJECT.toString());
            }
            template.send("product-orders", orderMessage.getId(), orderMessage);
        }
    }

    public void confirm(OrderMessage orderMessage) {

        // create list array source fail, in else if rollback, check current service name is in that array --> rollback logic
        Map<Long, Integer> map = new HashMap<>();
        orderMessage.getProductItems().forEach(item -> map.put(item.getProductId(), item.getQuantity()));
        List<Product> products = productRepository.findAllById(map.keySet());
        if (orderMessage.getStatus().equals(OrderStatus.CONFIRMED.toString())) {
            System.out.println("product confirm");
            List<Product> newProducts = products.stream().map(product -> {
                product.setReservedItem(product.getReservedItem() - map.get(product.getId()));
                return product;
            }).toList();
            productRepository.saveAll(newProducts);
        } else if (orderMessage.getStatus().equals(OrderStatus.ROLLBACK.toString())
                && orderMessage.getRollbackServices().contains(SERVICE)) {
            System.out.println("product rollback");
            List<Product> newProducts = products.stream().map(product -> {
                product.setReservedItem(product.getReservedItem() - map.get(product.getId()));
                product.setAvailableItem(product.getAvailableItem() + map.get(product.getId()));
                return product;
            }).toList();
            productRepository.saveAll(newProducts);
        }
    }
}
