package vn.whatsenglish.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.whatsenglish.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
