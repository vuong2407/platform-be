package vn.whatsenglish.product.service;

import vn.whatsenglish.ProductInfoResponseDto;
import vn.whatsenglish.product.dto.request.CreateProductRequestDTO;
import vn.whatsenglish.product.entity.Product;

import java.util.List;

public interface IProductService {
    ProductInfoResponseDto getProductById(Integer id);
    Product createProduct(CreateProductRequestDTO body);
    void addDiscountToProduct(List<Integer> discountIds, Integer productId);
}
