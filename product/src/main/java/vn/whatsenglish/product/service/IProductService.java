package vn.whatsenglish.product.service;

import vn.whatsenglish.product.dto.response.ProductResponseDTO;

public interface IProductService {
    ProductResponseDTO getProductById(Integer id);
}
