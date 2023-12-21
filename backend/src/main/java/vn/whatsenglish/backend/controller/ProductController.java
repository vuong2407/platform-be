package vn.whatsenglish.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.AddDiscountToProductRequestDto;
import vn.whatsenglish.CreateProductRequestDto;
import vn.whatsenglish.backend.dto.response.ProductDto;
import vn.whatsenglish.backend.service.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequestDto body) {
        return new ResponseEntity<>(productService.createProduct(body), HttpStatus.OK);
    }

    @PostMapping("/discount/add")
    public ResponseEntity<String> addDiscount(@RequestBody AddDiscountToProductRequestDto body) {
        productService.addDiscount(body);
        return new ResponseEntity<>("add discount success", HttpStatus.OK);
    }
}
