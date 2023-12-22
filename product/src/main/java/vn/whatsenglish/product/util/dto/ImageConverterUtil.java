package vn.whatsenglish.product.util.dto;

import vn.whatsenglish.domain.dto.product.response.InfoImageDto;
import vn.whatsenglish.product.entity.Image;

public class ImageConverterUtil {

    public static InfoImageDto toDto(Image image) {
        return InfoImageDto.builder()
                .id(image.getId())
                .url(image.getUrl())
                .build();
    }
}
