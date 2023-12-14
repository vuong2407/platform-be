package vn.whatsenglish.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.product.entity.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDTO {

    private Integer productCategoryId;
    private String displayName;
    private Float price;
    private String description;
    private String thumbnail;
    private List<String> imageUrls;

    public void validateAttribute() {
        // todo: need to validate all attribute before
    }
}
