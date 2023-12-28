package vn.whatsenglish.payment.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.whatsenglish.domain.dto.customer.request.UpdateCustomerRequestDto;
import vn.whatsenglish.domain.dto.customer.response.CustomerResponseDto;
import vn.whatsenglish.domain.dto.product.ResponseBodyDto;

public interface ServiceCustomer {
    @GET("/customer/{id}")
    Call<CustomerResponseDto> getCustomerById(@Path("id") String id);

    @POST("/customer/update")
    Call<ResponseBodyDto> updateCustomer(@Body UpdateCustomerRequestDto body);
}
