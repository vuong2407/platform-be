package vn.whatsenglish.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;
import vn.whatsenglish.backend.service.retrofit.ProductService;
import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;
import vn.whatsenglish.domain.dto.product.response.ProductResponseDto;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getProduct(@PathVariable String id)  {
        Response<ProductResponseDto> response = productService.getProductById(id);
        return this.handleResponse(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequestDto body) {
        Response<ProductResponseDto> response = productService.createProduct(body);
        return this.handleResponse(response);
    }
}
