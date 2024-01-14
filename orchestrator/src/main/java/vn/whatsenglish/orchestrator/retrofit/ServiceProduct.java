package vn.whatsenglish.orchestrator.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import vn.whatsenglish.domain.dto.product.request.DeductProductRequestDto;
import vn.whatsenglish.domain.dto.product.response.DeductProductResponseDto;

public interface ServiceProduct {
    @POST("/product/deduct")
    Call<DeductProductResponseDto> deductProduct(@Body DeductProductRequestDto body);

    @POST("/product/deduct/revert")
    Call<Object> revertDeductingProduct(@Body DeductProductRequestDto body);
}
