package vn.whatsenglish.domain.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.domain.dto.product.ProductItemDto;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private String orderCode;
    private Long customerId;
    private Long shippingAddressId;
    private List<ProductItemDto> productItems;
    private String orderStatus;
}
