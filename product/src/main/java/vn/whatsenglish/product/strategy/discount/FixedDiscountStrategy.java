package vn.whatsenglish.product.strategy.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FixedDiscountStrategy implements IDiscountStrategy{

    private Float fixedDiscount;

    @Override
    public float caculateFinalPrize(float prize) {
        return fixedDiscount;
    }
}
