package vn.whatsenglish.domain.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoItemDto {
    private Float price;
    private Integer productId;
    private Integer quantity;
}
