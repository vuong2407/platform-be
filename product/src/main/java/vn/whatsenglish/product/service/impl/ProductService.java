package vn.whatsenglish.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.whatsenglish.InfoCategoryOfProductDto;
import vn.whatsenglish.InfoImageDto;
import vn.whatsenglish.ProductInfoResponseDto;
import vn.whatsenglish.product.constant.Messages;
import vn.whatsenglish.product.constant.Parameters;
import vn.whatsenglish.product.dto.request.CreateProductRequestDTO;
import vn.whatsenglish.product.entity.Discount;
import vn.whatsenglish.product.entity.Image;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.entity.ProductCategory;
import vn.whatsenglish.product.exception.BadRequestException;
import vn.whatsenglish.product.exception.NotFoundException;
import vn.whatsenglish.product.repository.ProductCategoryRepository;
import vn.whatsenglish.product.repository.ProductRepository;
import vn.whatsenglish.product.service.IDiscountService;
import vn.whatsenglish.product.service.IImageService;
import vn.whatsenglish.product.service.IProductService;
import vn.whatsenglish.product.util.ObjectsUtil;
import vn.whatsenglish.product.util.dto.ProductDtoUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    IImageService imageService;

    @Autowired
    IDiscountService discountService;

    @Override
    public ProductInfoResponseDto getProductById(Integer id) {
        ObjectsUtil.checkRequiredParameters(id, Parameters.PRODUCT_ID_ATTRIBUTE);
        Optional<Product> optional = productRepository.findById(id);
        optional.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
        Product product = optional.get();
        return ProductDtoUtil.toProductInfoDto(product);
    }

    @Override
    public Product createProduct(CreateProductRequestDTO body) {
        // todo: validate body

        try {
            Integer productCategoryId = body.getProductCategoryId();
            ProductCategory productCategory = productCategoryRepository
                    .findById(productCategoryId).orElseThrow(
                            () -> new BadRequestException(MessageFormat.format(Messages.PRODUCT_CATEGORY_NOT_FOUND, productCategoryId)));
            Product product = Product.ofDto(body);
            product.setProductCategory(productCategory);
            Product result = productRepository.save(product);
            List<Image> images = imageService.saveAllImages(body.getImageUrls(), result);
            result.setImages(images);
            return result;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void addDiscountToProduct(List<Integer> discountIds, Integer productId) {
        try {
//            List<Discount> discounts = discountService.getAllDiscountsByListIds(discountIds);
//            Product product = getProductById(productId);
//            product.getDiscounts().addAll(discounts);
//            productRepository.save(product);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
