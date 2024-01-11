package vn.whatsenglish.domain.dto.payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.whatsenglish.domain.dto.payment.request.PaymentRequestDto;
import vn.whatsenglish.domain.enums.OrderStatus;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto extends PaymentRequestDto {
    OrderStatus status;
}
