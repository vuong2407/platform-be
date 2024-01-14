package vn.whatsenglish.orchestrator.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.whatsenglish.domain.dto.customer.request.UpdateCustomerRequestDto;
import vn.whatsenglish.domain.dto.customer.response.CustomerResponseDto;
import vn.whatsenglish.domain.dto.payment.request.PaymentRequestDto;
import vn.whatsenglish.domain.dto.payment.response.PaymentResponseDto;
import vn.whatsenglish.domain.dto.product.ResponseBodyDto;

public interface ServiceUser {
    @POST("/backend/user/deduct-payment")
    Call<PaymentResponseDto> deductPayment(@Body PaymentRequestDto body);

    @POST("/backend/user/deduct-payment/revert")
    Call<Object> revertDeductingPayment(@Body PaymentRequestDto body);
}
