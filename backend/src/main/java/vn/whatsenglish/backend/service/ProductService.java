package vn.whatsenglish.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import vn.whatsenglish.backend.dto.ProductDto;
import vn.whatsenglish.backend.retrofit.ServiceProduct;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ServiceProduct serviceProduct;

    public ProductDto getProductById(String id) {
        try {
            Response<ProductDto> call = serviceProduct.getProductById(id).execute();
            return call.body();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
