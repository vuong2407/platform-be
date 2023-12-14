package vn.whatsenglish.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiscountRequestDTO {

    private String nameDiscount;
    private String description;
    private String codeDiscount;
    private Integer percentageDiscount;
    private Float fixedDiscount;
    private Integer discountCategory;

    public void validateAttribute() {
        // todo: need to validate all attribute before
    }
}
