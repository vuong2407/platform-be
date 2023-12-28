package vn.whatsenglish.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.whatsenglish.domain.dto.product.request.CreateDiscountRequestDto;
import vn.whatsenglish.domain.dto.product.response.DiscountResponseDto;
import vn.whatsenglish.product.constant.Messages;
import vn.whatsenglish.product.entity.Discount;
import vn.whatsenglish.product.entity.DiscountCategory;
import vn.whatsenglish.product.exception.BadRequestException;
import vn.whatsenglish.product.repository.DiscountCategoryRepository;
import vn.whatsenglish.product.repository.DiscountRepository;
import vn.whatsenglish.product.service.IDiscountService;
import vn.whatsenglish.product.util.dto.DiscountConverterUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiscountService implements IDiscountService {

    @Autowired
    DiscountCategoryRepository discountCategoryRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public DiscountResponseDto createDiscount(CreateDiscountRequestDto body) {
        // todo: validate body

        DiscountCategory discountCategory = (DiscountCategory) discountCategoryRepository.findById(body.getDiscountCategory())
                .orElseThrow(() -> new BadRequestException("sfd"));
        Discount discount = Discount.ofDto(body);
        discount.setDiscountCategory(discountCategory);
        try {
            return DiscountConverterUtil.toDto(discountRepository.save(discount));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public List<Discount> getAllDiscountsByListIds(List<Long> discountIds) {
        List<Discount> discounts = discountRepository.findAllById(discountIds);
        Map<Long, Object> checkMap = new HashMap<>();
        discounts.forEach(discount -> checkMap.put(discount.getId(), null));
        List<Long> discountIdsNotExist = validateDiscountIds(discountIds, checkMap);
        if (!discountIdsNotExist.isEmpty()) {
            throw new BadRequestException(MessageFormat.format(Messages.DISCOUNT_ID_NOT_FOUND, discountIdsNotExist.toString()));
        }
        return discounts;
    }

    private List<Long> validateDiscountIds(List<Long> discountIds, Map<Long, Object> checkMap) {
        return discountIds.stream().filter(id -> !checkMap.containsKey(id)).toList();
    }
}
