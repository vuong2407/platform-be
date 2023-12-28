package vn.whatsenglish.domain.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.domain.dto.product.ProductItemDto;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateOrderRequestDto {
    private String orderCode;
    private Long customerId;
    private Long shippingAddressId;
    private Float totalPrice;
    private List<ProductItemDto> productItems = new ArrayList<>();
    private String orderStatus;
}
