package vn.whatsenglish.product.service;

import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;
import vn.whatsenglish.product.entity.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);
    Product createProduct(CreateProductRequestDto body);
    void addDiscountToProduct(List<Long> discountIds, Long productId);
}
