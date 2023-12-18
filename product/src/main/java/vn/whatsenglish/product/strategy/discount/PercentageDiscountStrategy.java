package vn.whatsenglish.product.strategy.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import vn.whatsenglish.product.entity.Discount;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class PercentageDiscountStrategy implements IDiscountStrategy {

    private Discount discount;

    @Override
    public float caculateFinalPrize(float prize) {
        return prize * ((float) discount.getPercentageDiscount() /100);
    }
}
