package vn.whatsenglish.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Response;
import vn.whatsenglish.backend.dto.ProductDto;
import vn.whatsenglish.backend.retrofit.ServiceProduct;
import vn.whatsenglish.domain.dto.product.response.ProductResponseDto;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceProduct serviceProduct;

    public ProductResponseDto getProductById(String id) {
        ProductResponseDto product = restTemplate.getForObject("http://localhost:8080/product/{id}", ProductResponseDto.class, 1);
        return product;
    }
}
