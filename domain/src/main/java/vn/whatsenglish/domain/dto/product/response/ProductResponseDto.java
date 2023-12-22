package vn.whatsenglish.domain.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private int id;
    private InfoCategoryOfProductDto productCategory;
    private String displayName;
    private float price;
    private Float displayPrice;
    private String description;
    private String thumbnail;
    private List<InfoImageDto> images;
}
