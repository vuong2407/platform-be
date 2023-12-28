package vn.whatsenglish.domain.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDto {

    private Long productCategoryId;
    private String displayName;
    private Float price;
    private String description;
    private String thumbnail;
    private List<String> imageUrls;
}
