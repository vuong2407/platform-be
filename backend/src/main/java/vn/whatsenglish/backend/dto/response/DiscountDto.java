package vn.whatsenglish.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {

    private Integer id;
    private String nameDiscount;
    private String description;
    private String codeDiscount;
    private Integer percentageDiscount;
    private Float fixedDiscount;
    private Integer discountCategoryId;
}
