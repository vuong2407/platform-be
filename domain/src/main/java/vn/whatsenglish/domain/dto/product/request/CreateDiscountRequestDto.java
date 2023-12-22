package vn.whatsenglish.domain.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiscountRequestDto {

    private String nameDiscount;
    private String description;
    private String codeDiscount;
    private Integer percentageDiscount;
    private Float fixedDiscount;
    private Integer discountCategory;
}
