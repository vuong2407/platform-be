package vn.whatsenglish.product.util.dto;

import vn.whatsenglish.DiscountResponseDto;
import vn.whatsenglish.product.entity.Discount;

public class DiscountConverterUtil {

    public static DiscountResponseDto toDiscountInfoDto(Discount discount) {
        return DiscountResponseDto.newBuilder()
                .setId(discount.getId())
                .setNameDiscount(discount.getNameDiscount())
                .setDescription(discount.getDescription())
                .setCodeDiscount(discount.getCodeDiscount())
                .setPercentageDiscount(discount.getPercentageDiscount())
                .setFixedDiscount(discount.getFixedDiscount())
                .setDiscountCategoryId(discount.getDiscountCategory().getId())
                .build();
    }
}
