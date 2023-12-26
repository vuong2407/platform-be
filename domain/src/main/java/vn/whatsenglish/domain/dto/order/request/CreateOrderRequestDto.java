package vn.whatsenglish.domain.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateOrderRequestDto {
    private String orderCode;
    private Integer customerId;
    private Integer shippingAddressId;
    private List<InfoItemDto> items = new ArrayList<>();
    private int orderStatus;
}
