package co.istad.thoung.customer_service.application;

import co.istad.thoung.customer_service.application.dto.create.CreateCustomerRequest;
import co.istad.thoung.customer_service.application.dto.create.CreateCustomerResponse;
import co.istad.thoung.customer_service.application.dto.update.ChangePhoneNumberRequest;
import co.istad.thoung.customer_service.application.dto.update.ChangePhoneNumberResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    ChangePhoneNumberResponse changePhoneNumber(UUID customerId, ChangePhoneNumberRequest changePhoneNumberRequest);
    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    ResponseEntity<Boolean> existsCustomer(UUID customerId);
}
