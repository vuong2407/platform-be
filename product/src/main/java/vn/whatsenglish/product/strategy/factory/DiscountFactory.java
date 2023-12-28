package vn.whatsenglish.product.strategy.factory;

import vn.whatsenglish.product.constant.Messages;
import vn.whatsenglish.product.entity.Discount;
import vn.whatsenglish.product.enums.Discounts;
import vn.whatsenglish.product.exception.BadRequestException;
import vn.whatsenglish.product.strategy.discount.IDiscountStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class DiscountFactory {

    private static final Map<Long, Class<? extends IDiscountStrategy>> discountRegister = new HashMap<>();

    static {
        for (Discounts discount : Discounts.values()) {
            discountRegister.put(discount.getId(), discount.getDiscountStrategy());
        }
    }

    public static void registerDiscount(Long key, Class<? extends IDiscountStrategy> _class) {
        discountRegister.put(key, _class);
    }

    public static IDiscountStrategy getDiscount(Long key, Discount discount) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends IDiscountStrategy> clazz = discountRegister.get(key);
        if (clazz == null) throw new BadRequestException(MessageFormat.format(Messages.DISCOUNT_CATEGORY_NOT_FOUND, key));
        Constructor<? extends IDiscountStrategy> discountStrategyConstructor = clazz.getDeclaredConstructor(discount.getClass());
        return (IDiscountStrategy) discountStrategyConstructor.newInstance(discount);
    }
}
