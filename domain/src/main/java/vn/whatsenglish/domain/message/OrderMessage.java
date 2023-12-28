package vn.whatsenglish.domain.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.domain.dto.product.ProductItemDto;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderMessage {
    private Long id;
    private Long customerId;
    private List<ProductItemDto> productItems;
    private Float totalPrice;
    private String status;
    private List<String> rollbackServices = new ArrayList<>();
}
