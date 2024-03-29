package vn.whatsenglish.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import vn.whatsenglish.domain.dto.product.ProductItemDto;
import vn.whatsenglish.domain.dto.product.request.CreateProductRequestDto;
import vn.whatsenglish.domain.dto.product.request.DeductProductRequestDto;
import vn.whatsenglish.domain.dto.product.response.DeductProductResponseDto;
import vn.whatsenglish.domain.enums.OrderStatus;
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

    @Autowired
    private PlatformTransactionManager transactionManager;

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

    @Override
//    @Transactional
    public DeductProductResponseDto deductProduct(DeductProductRequestDto request) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        definition.setTimeout(10000);
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            List<ProductItemDto> items = request.getItems();
            DeductProductResponseDto response = DeductProductResponseDto.builder()
                    .orderId(request.getOrderId())
                    .userId(request.getUserId())
                    .items(request.getItems())
                    .status(OrderStatus.ACCEPT)
                    .build();
            for (ProductItemDto item : items) {
                Optional<Product> optional = productRepository.findById(item.getProductId());
                optional.orElseThrow(() -> new BadRequestException(MessageFormat.format(Messages.PRODUCT_CATEGORY_NOT_FOUND, item.getProductId())));
                Product product = optional.get();
                if (product.getAvailableItem() < item.getQuantity()) {
                    response.setStatus(OrderStatus.REJECT);
                    transactionManager.rollback(status);
                    return response;
                }
                product.setAvailableItem(product.getAvailableItem() - item.getQuantity());
                productRepository.save(product);
            }
            transactionManager.commit(status);
            return response;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void revertDeductingProduct(DeductProductRequestDto request) {
        List<ProductItemDto> items = request.getItems();
        for (ProductItemDto item : items) {
            Product product = getProductById(item.getProductId());
            product.setAvailableItem(product.getAvailableItem() + item.getQuantity());
            productRepository.save(product);
        }
    }

    private Product getProductByIdCommon(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        optional.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
        return optional.get();
    }
}
