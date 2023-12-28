package vn.whatsenglish.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.customer.service.ICustomerService;
import vn.whatsenglish.customer.util.dto.CustomerConverterUtil;
import vn.whatsenglish.domain.dto.customer.request.UpdateCustomerRequestDto;
import vn.whatsenglish.domain.dto.customer.response.CustomerResponseDto;
import vn.whatsenglish.domain.dto.product.ResponseBodyDto;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Integer id) {
        return new ResponseEntity<>(CustomerConverterUtil.convertCustomerEntityToDto(customerService.getCustomerById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseBodyDto> updateCustomer(@RequestBody UpdateCustomerRequestDto body) {
        customerService.updateCustomer(body);
        return ResponseEntity.ok(new ResponseBodyDto("success", 123456L));
    }

}
