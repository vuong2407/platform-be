package vn.whatsenglish.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.backend.dto.ImageDto;
import vn.whatsenglish.backend.dto.ProductCategoryDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String displayName;
    private float price;
    private Float displayPrice;
    private String description;
    private String thumbnail;
    private ProductCategoryDto productCategory;
    private List<ImageDto> images;
}
