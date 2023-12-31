package vn.whatsenglish.domain.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDiscountToProductRequestDto {

    private Long productId;
    private List<Long> discountIds;
}
