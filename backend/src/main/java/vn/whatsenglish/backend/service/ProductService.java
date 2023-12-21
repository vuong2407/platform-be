package vn.whatsenglish.backend.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import vn.whatsenglish.AddDiscountToProductRequestDto;
import vn.whatsenglish.CreateDiscountRequestDto;
import vn.whatsenglish.CreateProductRequestDto;
import vn.whatsenglish.GetProductInfoRequestDto;
import vn.whatsenglish.ProductServiceGrpc;
import vn.whatsenglish.backend.dto.response.DiscountDto;
import vn.whatsenglish.backend.dto.response.ProductDto;
import vn.whatsenglish.backend.util.MessageUtil;

@Service
public class ProductService {

    @GrpcClient("grpc-product-service")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public ProductDto getProductById(String id) {
        GetProductInfoRequestDto request = GetProductInfoRequestDto.newBuilder().setProductId(Integer.parseInt(id)).build();
        return MessageUtil.convertMessageToDto(productServiceBlockingStub.getProductById(request), ProductDto.class);
    }

    public ProductDto createProduct(CreateProductRequestDto request) {
        return MessageUtil.convertMessageToDto(productServiceBlockingStub.createProduct(request), ProductDto.class);
    }

    public void addDiscount(AddDiscountToProductRequestDto request) {
        productServiceBlockingStub.addDiscountToProduct(request);
    }

    public DiscountDto crateDiscount(CreateDiscountRequestDto request) {
        return MessageUtil.convertMessageToDto(productServiceBlockingStub.createDiscount(request), DiscountDto.class);
    }
}
