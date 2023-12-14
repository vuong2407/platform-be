package vn.whatsenglish.product.service;

import vn.whatsenglish.product.dto.request.CreateProductRequestDTO;
import vn.whatsenglish.product.entity.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Integer id);
    Product createProduct(CreateProductRequestDTO body);
    void addDiscountToProduct(List<Integer> discountIds, Integer productId);
}
