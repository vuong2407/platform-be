package vn.whatsenglish.order;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import vn.whatsenglish.domain.message.PlacingOrderMessageResponse;
import vn.whatsenglish.order.service.IOrderService;

@SpringBootApplication
@EnableKafka
public class OrderApplication {

	@Autowired
	private IOrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
	public NewTopic orderRequests() {
		return TopicBuilder.name("order-requests")
				.partitions(1)
				.compact()
				.build();
	}

	@Bean
	public NewTopic orderResponses() {
		return TopicBuilder.name("order-responses")
				.partitions(1)
				.compact()
				.build();
	}

	@KafkaListener(id = "orchestrator", topics = "order-responses", groupId = "orchestrator")
	public void onEventResponsePlacingOrder(PlacingOrderMessageResponse placingOrderMessageResponse) {
		orderService.updateStatusOrder(placingOrderMessageResponse.getOrderId(), placingOrderMessageResponse.getOrderStatus().toString());
	}

}
