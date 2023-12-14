package vn.whatsenglish.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.product.entity.Image;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoImageDTO {

    private Integer id;
    private String url;

    public static InfoImageDTO ofEntity(Image image) {
        return InfoImageDTO.builder()
                .id(image.getId())
                .url(image.getUrl())
                .build();
    }
}
