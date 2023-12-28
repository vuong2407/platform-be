package vn.whatsenglish.domain.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountResponseDto {
    private Long id;
    private String nameDiscount;
    private String description;
    private String codeDiscount;
    private Integer percentageDiscount;
    private Float fixedDiscount;
    private Long discountCategoryId;
}
