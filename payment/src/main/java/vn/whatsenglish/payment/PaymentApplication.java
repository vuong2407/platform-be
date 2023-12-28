package vn.whatsenglish.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.message.OrderMessage;
import vn.whatsenglish.payment.service.OrderManagementService;

@SpringBootApplication
@EnableKafka
public class PaymentApplication {

	@Autowired
	private OrderManagementService orderManagementService;

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@KafkaListener(id = "orders", topics = "orders", groupId = "payment")
	public void onEvent(OrderMessage orderMessage) {
		if (orderMessage.getStatus().equals(OrderStatus.NEW.toString()))
			orderManagementService.reserveOrder(orderMessage);
		else {
			orderManagementService.confirmOrder(orderMessage);
		}
	}

}
