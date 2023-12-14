package vn.whatsenglish.product.util;

import vn.whatsenglish.product.entity.Discount;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.service.impl.ProcessDiscountService;
import vn.whatsenglish.product.strategy.discount.IDiscountStrategy;

import java.util.List;

public class DiscountPrizeCaculationUtil {

    public static float caculateFinalPrice(Product product) {
        if (product.getDiscounts().isEmpty()) return product.getPrice();
        final float currentPrice = product.getPrice();
        return product.getDiscounts().stream().reduce(currentPrice, (accumulator, element) -> {
            ProcessDiscountService processDiscountService = new ProcessDiscountService();
            IDiscountStrategy discountStrategy = processDiscountService.processDiscount(element);
            return accumulator - discountStrategy.caculateFinalPrize(currentPrice);
        }, Float::sum);
    }
}
