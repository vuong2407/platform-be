package vn.whatsenglish.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.product.dto.response.ProductResponseDTO;
import vn.whatsenglish.product.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> retrieveProductById(@PathVariable String id) {
        ProductResponseDTO productResponseDTO = productService.getProductById(Integer.parseInt(id));
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }
}
