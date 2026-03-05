package co.istad.thoung.customer_service.rest;

import co.istad.thoung.customer_service.application.CustomerService;
import co.istad.thoung.customer_service.application.dto.create.CreateCustomerRequest;
import co.istad.thoung.customer_service.application.dto.create.CreateCustomerResponse;
import co.istad.thoung.customer_service.application.dto.update.ChangePhoneNumberRequest;
import co.istad.thoung.customer_service.application.dto.update.ChangePhoneNumberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateCustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        log.info("create customer: {}", createCustomerRequest);
        return customerService.createCustomer(createCustomerRequest);
    }

    @PutMapping("/{customerId}/phone-number")
    public ChangePhoneNumberResponse changePhoneNumber(@PathVariable UUID customerId, @Valid @RequestBody ChangePhoneNumberRequest changePhoneNumberRequest) {
        log.info("change phone number: {}", changePhoneNumberRequest);
        return customerService.changePhoneNumber(customerId, changePhoneNumberRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> getCustomer(@PathVariable UUID id) {
        log.info("get customer: {}", id);
        return customerService.existsCustomer(id);
    }

}
