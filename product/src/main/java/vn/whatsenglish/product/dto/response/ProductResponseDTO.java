package vn.whatsenglish.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.product.entity.Image;
import vn.whatsenglish.product.entity.Product;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private int id;
    private InfoCategoryOfProductDTO productCategory;
    private String displayName;
    private float price;
    private String description;
    private String thumbnail;
    private List<Image> images;

    public static ProductResponseDTO ofEntity(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .productCategory(InfoCategoryOfProductDTO.ofEntity(product.getProductCategory()))
                .displayName(product.getDisplayName())
                .price(product.getPrice())
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .images(product.getImages())
                .build();
    }
}
