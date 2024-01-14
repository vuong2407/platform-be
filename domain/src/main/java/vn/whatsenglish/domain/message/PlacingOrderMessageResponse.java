package vn.whatsenglish.domain.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.domain.enums.OrderStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlacingOrderMessageResponse {
    private Integer orderId;
    private OrderStatus orderStatus;
}
