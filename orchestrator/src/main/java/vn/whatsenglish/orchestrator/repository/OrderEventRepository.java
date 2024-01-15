package vn.whatsenglish.orchestrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.whatsenglish.orchestrator.entity.OrderEvent;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Integer> {
}
