package vn.whatsenglish.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.whatsenglish.product.constant.Messages;
import vn.whatsenglish.product.constant.Parameters;
import vn.whatsenglish.product.dto.response.ProductResponseDTO;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.exception.NotFoundException;
import vn.whatsenglish.product.repository.ProductRepository;
import vn.whatsenglish.product.service.IProductService;
import vn.whatsenglish.product.util.ObjectsUtil;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDTO getProductById(Integer id) {
        ObjectsUtil.checkRequiredParameters(id, Parameters.PRODUCT_ID_ATTRIBUTE);
        Optional<Product> product = productRepository.findById(id);
        product.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
        ProductResponseDTO productResponseDTO = ProductResponseDTO.ofEntity(product.get());
        return ProductResponseDTO.ofEntity(product.get());
    }
}
