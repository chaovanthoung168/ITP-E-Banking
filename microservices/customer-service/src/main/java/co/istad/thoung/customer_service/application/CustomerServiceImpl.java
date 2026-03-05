package co.istad.thoung.customer_service.application;


import co.istad.thoung.common.domain.valueobject.CustomerId;
import co.istad.thoung.customer_service.application.dto.create.CreateCustomerRequest;
import co.istad.thoung.customer_service.application.dto.create.CreateCustomerResponse;
import co.istad.thoung.customer_service.application.dto.update.ChangePhoneNumberRequest;
import co.istad.thoung.customer_service.application.dto.update.ChangePhoneNumberResponse;
import co.istad.thoung.customer_service.application.mapper.CustomerApplicationMapper;
import co.istad.thoung.customer_service.application.repository.CustomerRepository;
import co.istad.thoung.customer_service.domain.command.ChangePhoneNumberCommand;
import co.istad.thoung.customer_service.domain.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerApplicationMapper customerMapper;
    private final CommandGateway commandGateway;
    private final CustomerRepository customerRepository;

    @Override
    public ChangePhoneNumberResponse changePhoneNumber(UUID customerId, ChangePhoneNumberRequest changePhoneNumberRequest) {

        // 1. Transfer data from request to command
        ChangePhoneNumberCommand changePhoneNumberCommand = ChangePhoneNumberCommand.builder()
                .customerId(new CustomerId(customerId))
                .phoneNumber(changePhoneNumberRequest.phoneNumber())
                .build();
        log.info("ChangePhoneNumberCommand: {}", changePhoneNumberCommand);

        UUID result = commandGateway.sendAndWait(changePhoneNumberCommand);

        return ChangePhoneNumberResponse.builder()
                .customerId(result)
                .phoneNumber(changePhoneNumberCommand.phoneNumber())
                .message("Phone number changed successfully")
                .build();
    }


    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {

        // 1. Transfer data from request to command
        CreateCustomerCommand createCustomerCommand = customerMapper
                .createCustomerRequestToCreateCustomerCommand(new CustomerId(UUID.randomUUID()), createCustomerRequest);
        log.info("CreateCustomerCommand: {}", createCustomerCommand);

        // 2. Invoke and handle Axon command gateway
        CustomerId result = commandGateway.sendAndWait(createCustomerCommand);
        log.info("CommandGateway Result: {}", result);

        return CreateCustomerResponse.builder()
                .customerId(createCustomerCommand.customerId().getValue())
                .message("Customer saved successfully")
                .build();
    }

    @Override
    public ResponseEntity<Boolean> existsCustomer(UUID customerId) {
        log.info("CustomerId: {}", customerId);
        boolean exists = customerRepository.existsById(customerId);

        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(true);
    }


}
