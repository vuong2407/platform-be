package vn.whatsenglish.backend.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;
import vn.whatsenglish.domain.dto.product.response.ProductResponseDto;

public interface ServiceProduct {

    @GET("/product/{id}")
    Call<ProductResponseDto> getProductById(@Path("id") String id);

    @POST("/product/create")
    Call<ProductResponseDto> createProduct(@Body CreateProductRequestDto body);
}
