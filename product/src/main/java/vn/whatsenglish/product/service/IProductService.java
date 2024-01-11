package vn.whatsenglish.product.service;

import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;
import vn.whatsenglish.domain.dto.product.request.DeductProductRequestDto;
import vn.whatsenglish.domain.dto.product.response.DeductProductResponseDto;
import vn.whatsenglish.product.entity.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);
    Product createProduct(CreateProductRequestDto body);
    void addDiscountToProduct(List<Long> discountIds, Long productId);
    DeductProductResponseDto deductProduct(DeductProductRequestDto request);
    void revertDeductingProduct(DeductProductRequestDto request);
}
