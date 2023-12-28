package vn.whatsenglish.order;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;
import vn.whatsenglish.domain.message.OrderMessage;
import vn.whatsenglish.order.service.impl.OrderManagementService;

import java.time.Duration;

@SpringBootApplication
@EnableKafkaStreams
@EnableKafka
public class OrderApplication {

	@Autowired
	OrderManagementService orderManagementService;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
	public NewTopic orders() {
		return TopicBuilder.name("orders")
				.partitions(3)
				.compact()
				.build();
	}

	@Bean
	public NewTopic paymentTopic() {
		return TopicBuilder.name("payment-orders")
				.partitions(3)
				.compact()
				.build();
	}

	@Bean
	public NewTopic stockTopic() {
		return TopicBuilder.name("product-orders")
				.partitions(3)
				.compact()
				.build();
	}

	@Bean
	public KStream<Long, OrderMessage> stream(StreamsBuilder builder) {
		JsonSerde<OrderMessage> orderSerde = new JsonSerde<>(OrderMessage.class);
		KStream<Long, OrderMessage> stream = builder
				.stream("payment-orders", Consumed.with(Serdes.Long(), orderSerde));

		stream.join(
						builder.stream("product-orders"),
						orderManagementService::confirm,
						JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(30)),
						StreamJoined.with(Serdes.Long(), orderSerde, orderSerde))
				.to("orders");
		return stream;
	}

}
