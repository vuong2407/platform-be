package vn.whatsenglish.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.whatsenglish.customer.entity.Customer;
import vn.whatsenglish.customer.repository.CustomerRepository;
import vn.whatsenglish.customer.service.ICustomerService;
import vn.whatsenglish.domain.dto.customer.request.UpdateCustomerRequestDto;
import vn.whatsenglish.domain.exception.BadRequestException;

import java.util.Optional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<Customer> optional = customerRepository.findById(id);
        optional.orElseThrow(() -> new BadRequestException("error get customer"));
        return optional.get();
    }

    @Override
    public void updateCustomer(UpdateCustomerRequestDto dto) {
        Customer customer = Customer.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .amountReserved(dto.getAmountReserved())
                .amountAvailable(dto.getAmountAvailable())
                .build();
        customerRepository.save(customer);
    }
}
