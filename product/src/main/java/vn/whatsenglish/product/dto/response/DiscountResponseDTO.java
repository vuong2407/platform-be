package vn.whatsenglish.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.product.entity.Discount;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountResponseDTO {

    private Integer id;
    private String nameDiscount;
    private String description;
    private String codeDiscount;
    private Integer percentageDiscount;
    private Float fixedDiscount;
    private Integer discountCategoryId;

    public static DiscountResponseDTO ofEntity(Discount discount) {
        return DiscountResponseDTO
                .builder()
                .id(discount.getId())
                .nameDiscount(discount.getNameDiscount())
                .description(discount.getDescription())
                .codeDiscount(discount.getCodeDiscount())
                .percentageDiscount(discount.getPercentageDiscount())
                .fixedDiscount(discount.getFixedDiscount())
                .discountCategoryId(discount.getDiscountCategory().getId())
                .build();
    }
}
