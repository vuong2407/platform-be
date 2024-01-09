package vn.whatsenglish.backend.service.retrofit;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import vn.whatsenglish.backend.retrofit.ServiceProduct;
import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;
import vn.whatsenglish.domain.dto.product.response.ProductResponseDto;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ServiceProduct serviceProduct;

    public Response<ProductResponseDto> getProductById(String id) {
        try {
            return serviceProduct.getProductById(id).execute();
        } catch (IOException e) {
            throw new RuntimeException("temp throw");
        }
    }

    public Response<ProductResponseDto> createProduct(CreateProductRequestDto body) {
        try {
            return serviceProduct.createProduct(body).execute();
        } catch (IOException e) {
            throw new RuntimeException("temp throw");
        }
    }
}
