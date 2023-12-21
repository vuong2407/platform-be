package vn.whatsenglish.product.service.impl;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import vn.whatsenglish.AddDiscountResponseDto;
import vn.whatsenglish.AddDiscountToProductRequestDto;
import vn.whatsenglish.CreateDiscountRequestDto;
import vn.whatsenglish.CreateProductRequestDto;
import vn.whatsenglish.DiscountResponseDto;
import vn.whatsenglish.GetProductInfoRequestDto;
import vn.whatsenglish.ProductInfoResponseDto;
import vn.whatsenglish.ProductServiceGrpc;
import vn.whatsenglish.product.constant.Messages;
import vn.whatsenglish.product.entity.Discount;
import vn.whatsenglish.product.entity.DiscountCategory;
import vn.whatsenglish.product.entity.Image;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.entity.ProductCategory;
import vn.whatsenglish.product.exception.BadRequestException;
import vn.whatsenglish.product.exception.NotFoundException;
import vn.whatsenglish.product.repository.DiscountCategoryRepository;
import vn.whatsenglish.product.repository.DiscountRepository;
import vn.whatsenglish.product.repository.ProductCategoryRepository;
import vn.whatsenglish.product.repository.ProductRepository;
import vn.whatsenglish.product.util.dto.DiscountConverterUtil;
import vn.whatsenglish.product.util.dto.ProductConverterUtil;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@GrpcService
@Transactional
public class ProductGrpc extends ProductServiceGrpc.ProductServiceImplBase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    DiscountService discountService;

    @Autowired
    DiscountCategoryRepository discountCategoryRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public void getProductById(GetProductInfoRequestDto request, StreamObserver<ProductInfoResponseDto> responseObserver) {
        try {
            Integer productId = request.getProductId();
            Optional<Product> optional = productRepository.findById(productId);
            optional.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
            Product product = optional.get();
            ProductInfoResponseDto result = ProductConverterUtil.toProductInfoDto(product);
            responseObserver.onNext(result);
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(new NotFoundException(e.getMessage()));
        }
    }

    @Override
    public void createProduct(CreateProductRequestDto request, StreamObserver<ProductInfoResponseDto> responseObserver) {
        try {
            Integer productCategoryId = request.getProductCategoryId();
            ProductCategory productCategory = productCategoryRepository
                    .findById(productCategoryId).orElseThrow(
                            () -> new BadRequestException(MessageFormat.format(Messages.PRODUCT_CATEGORY_NOT_FOUND, productCategoryId)));
            Product product = Product.ofDto(request);
            product.setProductCategory(productCategory);
            Product result = productRepository.save(product);
            List<Image> images = imageService.saveAllImages(request.getImageUrlsList(), result);
            result.setImages(images);
            responseObserver.onNext(ProductConverterUtil.toProductInfoDto(result));
            responseObserver.onCompleted();
        } catch (BadRequestException e) {
            responseObserver.onError(new BadRequestException(e.getMessage()));
        }
    }

    @Override
    public void addDiscountToProduct(AddDiscountToProductRequestDto request, StreamObserver<AddDiscountResponseDto> responseObserver) {
        try {
            List<Discount> discounts = discountService.getAllDiscountsByListIds(request.getDiscountIdsList());
            Product product = getProductByIdCommon(request.getProductId());
            product.getDiscounts().addAll(discounts);
            productRepository.save(product);
            responseObserver.onNext(AddDiscountResponseDto.newBuilder().build());
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            responseObserver.onError(new BadRequestException(e.getMessage()));
        }
    }

    @Override
    public void createDiscount(CreateDiscountRequestDto request, StreamObserver<DiscountResponseDto> responseObserver) {
        try {
            DiscountCategory discountCategory = (DiscountCategory) discountCategoryRepository.findById(request.getDiscountCategory())
                    .orElseThrow(() -> new BadRequestException("create discount fail"));
            Discount discount = Discount.ofDto(request);
            discount.setDiscountCategory(discountCategory);
            responseObserver.onNext(DiscountConverterUtil.toDiscountInfoDto(discountRepository.save(discount)));
            responseObserver.onCompleted();
        } catch (BadRequestException e) {
            responseObserver.onError(new BadRequestException(e.getMessage()));
        }
    }

    private Product getProductByIdCommon(Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        optional.orElseThrow(() -> new NotFoundException(Messages.DATA_IS_NOT_FOUND));
        return optional.get();
    }
}
