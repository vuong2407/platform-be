package vn.whatsenglish.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.CreateDiscountRequestDto;
import vn.whatsenglish.DiscountResponseDto;
import vn.whatsenglish.backend.dto.response.DiscountDto;
import vn.whatsenglish.backend.service.ProductService;

@RequestMapping("/discount")
@RestController
public class DiscountController extends BaseController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<DiscountDto> createDiscount(@RequestBody CreateDiscountRequestDto body) {
        DiscountDto response = productService.crateDiscount(body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
