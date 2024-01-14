package vn.whatsenglish.domain.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.domain.dto.product.ProductItemDto;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlacingOrderMessage {
    private Integer userId;
    private Integer orderId;
    private List<ProductItemDto> items;
    private Float amount;
}
