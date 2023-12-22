package vn.whatsenglish.product.util.dto;

import vn.whatsenglish.domain.dto.product.response.DiscountResponseDto;
import vn.whatsenglish.product.entity.Discount;

public class DiscountConverterUtil {

    public static DiscountResponseDto toDto(Discount discount) {
        return DiscountResponseDto.builder()
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
