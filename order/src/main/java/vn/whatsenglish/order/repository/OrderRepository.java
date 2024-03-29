package vn.whatsenglish.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.whatsenglish.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
