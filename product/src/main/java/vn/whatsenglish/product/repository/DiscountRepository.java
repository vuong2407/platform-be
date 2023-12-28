package vn.whatsenglish.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.whatsenglish.product.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
