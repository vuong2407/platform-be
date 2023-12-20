package vn.whatsenglish.product.controller;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.ProductInfoResponseDto;
import vn.whatsenglish.product.dto.request.AddDiscountToProductRequestDTO;
import vn.whatsenglish.product.dto.request.CreateProductRequestDTO;
import vn.whatsenglish.product.dto.response.ProductResponseDTO;
import vn.whatsenglish.product.entity.Discount;
import vn.whatsenglish.product.entity.Product;
import vn.whatsenglish.product.service.IDiscountService;
import vn.whatsenglish.product.service.IProductService;
import vn.whatsenglish.product.util.DiscountPrizeCaculationUtil;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IDiscountService discountService;

    @GetMapping("/{id}")
    public ResponseEntity<String> retrieveProductById(@PathVariable String id) throws InvalidProtocolBufferException {
        ProductInfoResponseDto product = productService.getProductById(Integer.parseInt(id));
        String productJson = JsonFormat.printer().print(product);
        return new ResponseEntity<>(productJson, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO body) {
        ProductResponseDTO productResponseDTO = (new ProductResponseDTO()).ofEntity(productService.createProduct(body));
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @PostMapping("discount/add")
    public ResponseEntity<?> addDiscountToProduct(@RequestBody AddDiscountToProductRequestDTO body) {
        productService.addDiscountToProduct(body.getDiscountIds(), body.getProductId());
        return null;
    }
}
