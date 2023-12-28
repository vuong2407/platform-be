package vn.whatsenglish.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.message.OrderMessage;
import vn.whatsenglish.product.service.kafka.OrderManagementService;

@SpringBootApplication
@EnableKafka
public class ProductApplication {

	@Autowired
	private OrderManagementService orderManagementService;

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@KafkaListener(id = "orders", topics = "orders", groupId = "stock")
	public void onEvent(OrderMessage orderMessage) {
		if (orderMessage.getStatus().equals(OrderStatus.NEW.toString())) {
			orderManagementService.reserve(orderMessage);
		} else {
			orderManagementService.confirm(orderMessage);
		}
	}

}
