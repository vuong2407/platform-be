package vn.whatsenglish.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.AddDiscountToProductRequestDto;
import vn.whatsenglish.CreateProductRequestDto;
import vn.whatsenglish.ProductInfoResponseDto;
import vn.whatsenglish.product.service.IDiscountService;
import vn.whatsenglish.product.service.IProductService;
import vn.whatsenglish.product.util.dto.GrpcUtil;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IDiscountService discountService;

    @GetMapping("/{id}")
    public ResponseEntity<String> retrieveProductById(@PathVariable String id) {
        ProductInfoResponseDto product = productService.getProductById(Integer.parseInt(id));
        String productJson = GrpcUtil.covertMessageToString(product);
        return new ResponseEntity<>(productJson, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequestDto body)  {
        return new ResponseEntity<>(GrpcUtil.covertMessageToString(productService.createProduct(body)), HttpStatus.OK);
    }

    @PostMapping("discount/add")
    public ResponseEntity<?> addDiscountToProduct(@RequestBody AddDiscountToProductRequestDto body) {
        productService.addDiscountToProduct(body.getDiscountIdsList(), body.getProductId());
        return ResponseEntity.ok("add discount success");
    }
}
