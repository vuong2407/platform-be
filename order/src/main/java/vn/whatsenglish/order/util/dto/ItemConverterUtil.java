package vn.whatsenglish.order.util.dto;

import vn.whatsenglish.domain.dto.order.request.InfoItemDto;
import vn.whatsenglish.domain.dto.product.ProductItemDto;
import vn.whatsenglish.order.entity.Item;

public class ItemConverterUtil {
    public static Item convertInfoItemToEntity(ProductItemDto dto) {
        return Item.builder()
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .build();
    }
}
