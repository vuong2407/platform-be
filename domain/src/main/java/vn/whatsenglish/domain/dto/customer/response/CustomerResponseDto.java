package vn.whatsenglish.domain.dto.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CustomerResponseDto {
    private Integer id;
    private Integer userId;
    private Float amountAvailable;
    private Float amountReserved;
}
