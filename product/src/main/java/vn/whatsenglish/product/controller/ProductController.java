package vn.whatsenglish.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.domain.dto.product.request.AddDiscountToProductRequestDto;
import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;
import vn.whatsenglish.domain.dto.product.response.ProductResponseDto;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.service.IDiscountService;
import vn.whatsenglish.product.service.IProductService;
import vn.whatsenglish.product.util.dto.ProductConvertUtil;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IDiscountService discountService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> retrieveProductById(@PathVariable String id) {
        Product product = productService.getProductById(Long.parseLong(id));
        ProductResponseDto productResponseDTO = ProductConvertUtil.toDto(product);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody CreateProductRequestDto body) {
        ProductResponseDto productResponseDTO = ProductConvertUtil.toDto(productService.createProduct(body));
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @PostMapping("discount/add")
    public ResponseEntity<?> addDiscountToProduct(@RequestBody AddDiscountToProductRequestDto body) {
        productService.addDiscountToProduct(body.getDiscountIds(), body.getProductId());
        return null;
    }
}
