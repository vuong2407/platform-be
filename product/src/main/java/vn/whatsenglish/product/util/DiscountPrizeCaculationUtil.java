package vn.whatsenglish.product.util;

import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.strategy.factory.DiscountFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DiscountPrizeCaculationUtil {

    public static float caculateFinalPrice(Product product) {
        if (product.getDiscounts() == null || product.getDiscounts().isEmpty()) return product.getPrice();
        final float currentPrice = product.getPrice();
        return product.getDiscounts().stream().reduce(currentPrice, (accumulator, element) -> {
            Integer discountCategoryId = element.getDiscountCategory().getId();
            try {
                return accumulator - DiscountFactory.getDiscount(discountCategoryId, element).caculateFinalPrize(currentPrice);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }, Float::sum);
    }
}
