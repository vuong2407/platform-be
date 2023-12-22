package vn.whatsenglish.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.domain.dto.product.request.CreateDiscountRequestDto;
import vn.whatsenglish.domain.dto.product.response.DiscountResponseDto;
import vn.whatsenglish.product.service.IDiscountService;

@RequestMapping("/discount")
@RestController
public class DiscountController {

    @Autowired
    IDiscountService discountService;

    @PostMapping("/create")
    public ResponseEntity<DiscountResponseDto> createDiscount(@RequestBody CreateDiscountRequestDto body) {
        DiscountResponseDto payload = discountService.createDiscount(body);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}
