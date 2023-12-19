package vn.whatsenglish.product.strategy.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import vn.whatsenglish.product.entity.Discount;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FixedDiscountStrategy implements IDiscountStrategy{

    private Discount discount;

    @Override
    public float caculateFinalPrize(float prize) {
        return discount.getFixedDiscount();
    }
}
