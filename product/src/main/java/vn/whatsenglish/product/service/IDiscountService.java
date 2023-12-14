package vn.whatsenglish.product.service;

import vn.whatsenglish.product.dto.request.CreateDiscountRequestDTO;
import vn.whatsenglish.product.dto.response.DiscountResponseDTO;
import vn.whatsenglish.product.entity.Discount;
import vn.whatsenglish.product.entity.Product;

import java.util.List;

public interface IDiscountService {
    DiscountResponseDTO createDiscount(CreateDiscountRequestDTO body);

    List<Discount> getAllDiscountsByListIds(List<Integer> discountIds);
}
