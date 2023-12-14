package vn.whatsenglish.product.strategy.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class PercentageDiscountStrategy implements IDiscountStrategy {

    private Integer percentageDiscount;

    @Override
    public float caculateFinalPrize(float prize) {
        return prize * ((float) percentageDiscount /100);
    }
}
