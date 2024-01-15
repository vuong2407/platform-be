package vn.whatsenglish.orchestrator;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import vn.whatsenglish.domain.message.PlacingOrderMessage;
import vn.whatsenglish.domain.message.PlacingOrderMessageResponse;
import vn.whatsenglish.orchestrator.service.orchestrator.OrderOrchestrator;

@SpringBootApplication
@EnableKafka
public class OrchestratorApplication {

	@Autowired
	OrderOrchestrator orderOrchestrator;

	@Autowired
	KafkaTemplate<Integer, PlacingOrderMessageResponse> orderTemplate;

	public static void main(String[] args) {
		SpringApplication.run(OrchestratorApplication.class, args);
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

	@KafkaListener(id = "orchestrator", topics = "order-requests", groupId = "orchestrator")
	public void onEventPlacingOrder(PlacingOrderMessage placingOrderMessage) {
		PlacingOrderMessageResponse messageResponse = orderOrchestrator.orderProduct(placingOrderMessage);
		orderTemplate.send("order-responses", placingOrderMessage.getOrderId(), messageResponse);
	}

}
