package vn.whatsenglish.backend.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;
import vn.whatsenglish.backend.anotation.Test;
import vn.whatsenglish.backend.service.ProductService;
import vn.whatsenglish.domain.dto.product.ResponseBodyDto;
import vn.whatsenglish.domain.dto.product.response.ProductResponseDto;

import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    @Test
    public ResponseEntity<?> getProduct(@PathVariable String id)  {
        Response<ProductResponseDto> response = productService.getProductById(id);
        return ResponseEntity.ok(response.body());
    }
}
