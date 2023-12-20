package vn.whatsenglish.product.service;

import vn.whatsenglish.CreateProductRequestDto;
import vn.whatsenglish.ProductInfoResponseDto;

import java.util.List;

public interface IProductService {
    ProductInfoResponseDto getProductById(Integer id);
    ProductInfoResponseDto createProduct(CreateProductRequestDto body);
    void addDiscountToProduct(List<Integer> discountIds, Integer productId);
}
