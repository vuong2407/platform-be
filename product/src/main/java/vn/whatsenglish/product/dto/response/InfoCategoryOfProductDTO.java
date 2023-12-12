package vn.whatsenglish.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.product.entity.ProductCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoCategoryOfProductDTO {

    private int id;
    private String name;
    private int code;
    private String description;

    public static InfoCategoryOfProductDTO ofEntity(ProductCategory productCategory) {
        return InfoCategoryOfProductDTO.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .code(productCategory.getCode())
                .description(productCategory.getDescription())
                .build();
    }
}
