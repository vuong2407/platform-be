package vn.whatsenglish.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.product.entity.Discount;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddDiscountToProductRequestDTO {

    private Integer productId;
    private List<Integer> discountIds;
}
