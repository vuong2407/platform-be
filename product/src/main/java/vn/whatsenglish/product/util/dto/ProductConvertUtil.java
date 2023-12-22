package vn.whatsenglish.product.util.dto;

import vn.whatsenglish.domain.dto.product.response.InfoCategoryOfProductDto;
import vn.whatsenglish.domain.dto.product.response.InfoImageDto;
import vn.whatsenglish.domain.dto.product.response.ProductResponseDto;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.entity.ProductCategory;
import vn.whatsenglish.product.util.DiscountPrizeCaculationUtil;

import java.util.List;

public class ProductConvertUtil {

    public static ProductResponseDto toDto(Product product) {
        List<InfoImageDto> images = product.getImages().stream().map(ImageConverterUtil::toDto).toList();
        float displayPrize = DiscountPrizeCaculationUtil.caculateFinalPrice(product);
        return ProductResponseDto.builder()
                .id(product.getId())
                .productCategory(productCategoryToDto(product.getProductCategory()))
                .displayName(product.getDisplayName())
                .price(product.getPrice())
                .displayPrice(displayPrize)
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .images(images)
                .build();
    }

    public static InfoCategoryOfProductDto productCategoryToDto(ProductCategory productCategory) {
        return InfoCategoryOfProductDto.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .code(productCategory.getCode())
                .description(productCategory.getDescription())
                .build();
    }
}
