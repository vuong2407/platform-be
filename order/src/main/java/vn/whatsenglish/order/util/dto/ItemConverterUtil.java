package vn.whatsenglish.order.util.dto;

import vn.whatsenglish.domain.dto.order.request.InfoItemDto;
import vn.whatsenglish.order.entity.Item;

public class ItemConverterUtil {
    public static Item convertInfoItemToEntity(InfoItemDto dto) {
        return Item.builder()
                .price(dto.getPrice())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .build();
    }
}
