package vn.whatsenglish.orchestrator.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import vn.whatsenglish.domain.enums.OrderStatus;

@Entity(name = "order_events")
@Builder
@Data
public class OrderEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_status")
    private String orderStatus;
}
