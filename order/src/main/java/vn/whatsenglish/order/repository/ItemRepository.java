package vn.whatsenglish.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.whatsenglish.order.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
