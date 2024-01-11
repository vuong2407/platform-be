package vn.whatsenglish.domain.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.whatsenglish.domain.dto.product.request.DeductProductRequestDto;
import vn.whatsenglish.domain.enums.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeductProductResponseDto extends DeductProductRequestDto {
    OrderStatus status;
}
