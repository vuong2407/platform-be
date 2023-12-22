package vn.whatsenglish.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import vn.whatsenglish.domain.event.product.GetInfoProductEvent;

@Service
public class ProductConsumer {

    @Autowired
    ProductService productService;

    @KafkaListener(
            topics = "${kafka-topic.product}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void productTopicConsume(GetInfoProductEvent event){
        productService.getProductById(event.getId());
    }
}
