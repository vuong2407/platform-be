package vn.whatsenglish.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.whatsenglish.product.entity.Product;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private int id;
    private InfoCategoryOfProductDTO productCategory;
    private String displayName;
    private float price;
    private Float displayPrice;
    private String description;
    private String thumbnail;
    private List<InfoImageDTO> images;

    public ProductResponseDTO ofEntity(Product product) {
        Optional<Float> optional = Optional.ofNullable(this.getDisplayPrice());
        Float valueOfDisplayPrice = optional.orElse(product.getPrice());
        List<InfoImageDTO> images = product.getImages().stream().map(InfoImageDTO::ofEntity).toList();
        return ProductResponseDTO.builder()
                .id(product.getId())
                .productCategory(InfoCategoryOfProductDTO.ofEntity(product.getProductCategory()))
                .displayName(product.getDisplayName())
                .price(product.getPrice())
                .displayPrice(valueOfDisplayPrice)
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .images(images)
                .build();
    }
}
