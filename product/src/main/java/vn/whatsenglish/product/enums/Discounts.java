package vn.whatsenglish.product.enums;

import lombok.Getter;
import vn.whatsenglish.product.strategy.discount.FixedDiscountStrategy;
import vn.whatsenglish.product.strategy.discount.IDiscountStrategy;
import vn.whatsenglish.product.strategy.discount.PercentageDiscountStrategy;

public enum Discounts {

    FIXED_DISCOUNT(1, FixedDiscountStrategy.class),
    PERCENTAGE_DISCOUNT(2, PercentageDiscountStrategy.class);

    @Getter
    private final Integer id;
    @Getter
    private final Class<? extends IDiscountStrategy> discountStrategy;

    Discounts(Integer id, Class<? extends IDiscountStrategy> discountStrategy) {
        this.id = id;
        this.discountStrategy = discountStrategy;
    }

}
