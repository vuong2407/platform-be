package vn.whatsenglish.backend.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.whatsenglish.backend.dto.ProductDto;

public interface ServiceProduct {

    @GET("/product/{id}")
    Call<ProductDto> getProductById(@Path("id") String id);
}
