package vn.whatsenglish.domain.dto.payment.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentRequestDto {
    protected Integer userId;
    protected Integer orderId;
    protected Float amount;
}
