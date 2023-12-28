package vn.whatsenglish.product.enums;

import lombok.Getter;
import vn.whatsenglish.product.strategy.discount.FixedDiscountStrategy;
import vn.whatsenglish.product.strategy.discount.IDiscountStrategy;
import vn.whatsenglish.product.strategy.discount.PercentageDiscountStrategy;

public enum Discounts {

    FIXED_DISCOUNT(1L, FixedDiscountStrategy.class),
    PERCENTAGE_DISCOUNT(2L, PercentageDiscountStrategy.class);

    @Getter
    private final Long id;
    @Getter
    private final Class<? extends IDiscountStrategy> discountStrategy;

    Discounts(Long id, Class<? extends IDiscountStrategy> discountStrategy) {
        this.id = id;
        this.discountStrategy = discountStrategy;
    }

}
