package vn.whatsenglish.product.util.dto;

import vn.whatsenglish.InfoCategoryOfProductDto;
import vn.whatsenglish.InfoImageDto;
import vn.whatsenglish.ProductInfoResponseDto;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.util.DiscountPrizeCaculationUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductConverterUtil {

    public static ProductInfoResponseDto toProductInfoDto(Product product) {
        InfoCategoryOfProductDto productCategory = InfoCategoryOfProductDto.newBuilder()
                .setId(product.getProductCategory().getId())
                .setName(product.getProductCategory().getName())
                .setCode(product.getProductCategory().getCode())
                .setDescription(product.getProductCategory().getDescription())
                .build();
        List<InfoImageDto> images = new ArrayList<>();
        product.getImages().forEach(i -> images.add(InfoImageDto.newBuilder()
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
