package vn.whatsenglish.product.util.dto;

import vn.whatsenglish.Images;
import vn.whatsenglish.ProductCategory;
import vn.whatsenglish.ProductInfoResponseDto;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.strategy.factory.DiscountFactory;
import vn.whatsenglish.product.util.DiscountPrizeCaculationUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductConverterUtil {

    public static ProductInfoResponseDto toProductInfoDto(Product product) {
        ProductCategory productCategory = ProductCategory.newBuilder()
                .setId(product.getProductCategory().getId())
                .setName(product.getProductCategory().getName())
                .setCode(product.getProductCategory().getCode())
                .setDescription(product.getProductCategory().getDescription())
                .build();
        List<Images> images = new ArrayList<>();
        product.getImages().forEach(i -> images.add(Images.newBuilder()
                .setId(i.getId())
                .setUrl(i.getUrl())
                .build()));
        return ProductInfoResponseDto.newBuilder()
                .setId(product.getId())
                .setProductCategory(productCategory)
                .setDisplayName(product.getDisplayName())
                .setPrice(product.getPrice())
                .setDisplayPrice(DiscountPrizeCaculationUtil.caculateFinalPrice(product))
                .setDescription(product.getDescription())
                .setThumbnail(product.getThumbnail())
                .addAllImages(images)
                .build();
    }
}
