package vn.whatsenglish.domain.dto.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseInfoCustomerRequestDto {
    protected Integer userId;
    protected Float amountAvailable;
    protected Float amountReserved;
}
