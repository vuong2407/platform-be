package vn.whatsenglish.product.service.impl;

import org.springframework.stereotype.Service;
import vn.whatsenglish.product.entity.Discount;
import vn.whatsenglish.product.entity.DiscountCategory;
import vn.whatsenglish.product.strategy.discount.FixedDiscountStrategy;
import vn.whatsenglish.product.strategy.discount.IDiscountStrategy;
import vn.whatsenglish.product.strategy.discount.PercentageDiscountStrategy;

public class ProcessDiscountService {

    public IDiscountStrategy processDiscount(Discount discount) {
        return switch (discount.getDiscountCategory().getId()) {
            case DiscountCategory.FIXED_DISCOUNT -> new FixedDiscountStrategy(discount.getFixedDiscount());
            case DiscountCategory.PERCENTAGE_DISCOUNT -> new PercentageDiscountStrategy(discount.getPercentageDiscount());
            default -> throw new IllegalStateException("Unexpected value: " + discount.getDiscountCategory().getId());
        };
    }

}
