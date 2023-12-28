package vn.whatsenglish.customer.service;

import vn.whatsenglish.customer.entity.Customer;
import vn.whatsenglish.domain.dto.customer.request.UpdateCustomerRequestDto;

public interface ICustomerService {
    Customer getCustomerById(Integer id);
    void updateCustomer(UpdateCustomerRequestDto dto);
}
