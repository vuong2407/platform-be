package vn.whatsenglish.domain.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.whatsenglish.domain.dto.product.ProductItemDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeductProductRequestDto {
    protected Integer userId;
    protected Integer orderId;
    protected List<ProductItemDto> items;
}
