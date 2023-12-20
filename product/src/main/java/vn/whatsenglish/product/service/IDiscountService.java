package vn.whatsenglish.product.service;

import vn.whatsenglish.CreateDiscountRequestDto;
import vn.whatsenglish.DiscountResponseDto;
import vn.whatsenglish.product.entity.Discount;

import java.util.List;

public interface IDiscountService {
    DiscountResponseDto createDiscount(CreateDiscountRequestDto body);

    List<Discount> getAllDiscountsByListIds(List<Integer> discountIds);
}
