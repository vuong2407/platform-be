package vn.whatsenglish.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.whatsenglish.product.entity.DiscountCategory;

@Repository
public interface DiscountCategoryRepository extends JpaRepository<DiscountCategory, Long> {
}
