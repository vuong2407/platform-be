package vn.whatsenglish.product.service;

import vn.whatsenglish.domain.dto.product.request.CreateDiscountRequestDto;
import vn.whatsenglish.domain.dto.product.response.DiscountResponseDto;
import vn.whatsenglish.product.entity.Discount;

import java.util.List;

public interface IDiscountService {
    DiscountResponseDto createDiscount(CreateDiscountRequestDto body);

    List<Discount> getAllDiscountsByListIds(List<Integer> discountIds);
}
