package vn.whatsenglish.customer.util.dto;

import vn.whatsenglish.customer.entity.Customer;
import vn.whatsenglish.domain.dto.customer.response.CustomerResponseDto;

public class CustomerConverterUtil {
    public static CustomerResponseDto convertCustomerEntityToDto(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .userId(customer.getUserId())
                .amountAvailable(customer.getAmountAvailable())
                .amountReserved(customer.getAmountReserved())
                .build();
    }
}
