package vn.whatsenglish.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;
import vn.whatsenglish.product.constant.Messages;
import vn.whatsenglish.product.constant.Parameters;
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

import java.text.MessageFormat;
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
    public Product getProductById(Long id) {
        ObjectsUtil.checkRequiredParameters(id, Parameters.PRODUCT_ID_ATTRIBUTE);
        Optional<Product> product = productRepository.findById(id);
        product.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
        return product.get();
    }

    @Override
    public Product createProduct(CreateProductRequestDto body) {
        // todo: validate body

        try {
            Long productCategoryId = body.getProductCategoryId();
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
    public void addDiscountToProduct(List<Long> discountIds, Long productId) {
        try {
            List<Discount> discounts = discountService.getAllDiscountsByListIds(discountIds);
            Product product = getProductByIdCommon(productId);
            product.getDiscounts().addAll(discounts);
            productRepository.save(product);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private Product getProductByIdCommon(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        optional.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
        return optional.get();
    }
}
