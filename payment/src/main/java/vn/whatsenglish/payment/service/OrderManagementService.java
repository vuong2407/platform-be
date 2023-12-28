package vn.whatsenglish.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import vn.whatsenglish.domain.dto.customer.request.UpdateCustomerRequestDto;
import vn.whatsenglish.domain.dto.customer.response.CustomerResponseDto;
import vn.whatsenglish.domain.dto.product.ResponseBodyDto;
import vn.whatsenglish.domain.enums.OrderStatus;
import vn.whatsenglish.domain.message.OrderMessage;
import vn.whatsenglish.payment.retrofit.ServiceCustomer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class OrderManagementService {

    private static final String SERVICE = "payment";

    @Autowired
    private ServiceCustomer serviceCustomer;

    @Autowired
    private KafkaTemplate<Long, OrderMessage> template;

    public void reserveOrder(OrderMessage orderMessage) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        CustomerResponseDto customer = getCustomer(orderMessage.getCustomerId());
        System.out.println("payment reserve");
        if (orderMessage.getTotalPrice() < customer.getAmountAvailable()) {
            orderMessage.setStatus(OrderStatus.ACCEPT.toString());
            customer.setAmountReserved(customer.getAmountReserved() + orderMessage.getTotalPrice());
            customer.setAmountAvailable(customer.getAmountAvailable() - orderMessage.getTotalPrice());
            orderMessage.getRollbackServices().add(SERVICE);
        } else {
            orderMessage.setStatus((OrderStatus.REJECT.toString()));
        }
        if (isUpdateCustomerSuccess(customer)) {
            template.send("payment-orders", orderMessage.getId(), orderMessage);
        } else {
            System.out.println("fail update customer");
        }
    }

    public void confirmOrder(OrderMessage orderMessage) {
        CustomerResponseDto customer = getCustomer(orderMessage.getCustomerId());
        if (orderMessage.getStatus().equals(OrderStatus.CONFIRMED.toString())) {
            customer.setAmountReserved(customer.getAmountReserved() - orderMessage.getTotalPrice());
            System.out.println("payment confirm: confirmed order");
        } else if (orderMessage.getStatus().equals(OrderStatus.ROLLBACK.toString())
                && orderMessage.getRollbackServices().contains(SERVICE)) {
            System.out.println("payment confirm: rollback order");
            customer.setAmountReserved(customer.getAmountReserved() - orderMessage.getTotalPrice());
            customer.setAmountAvailable(customer.getAmountAvailable() + orderMessage.getTotalPrice());
        }
        isUpdateCustomerSuccess(customer);
    }

    private CustomerResponseDto getCustomer(Long id) {
        try {
            Response<CustomerResponseDto> response = serviceCustomer.getCustomerById(id.toString()).execute();
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private boolean isUpdateCustomerSuccess(CustomerResponseDto customer) {
        UpdateCustomerRequestDto dto = UpdateCustomerRequestDto.builder()
                .id(customer.getId())
                .userId(customer.getUserId())
                .amountAvailable(customer.getAmountAvailable())
                .amountReserved(customer.getAmountReserved())
                .build();
        try {
            Response<ResponseBodyDto> response = serviceCustomer.updateCustomer(dto).execute();
            return response.isSuccessful();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
