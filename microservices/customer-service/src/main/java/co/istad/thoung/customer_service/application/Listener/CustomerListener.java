package co.istad.thoung.customer_service.application.Listener;

import co.istad.thoung.customer_service.application.mapper.CustomerApplicationMapper;
import co.istad.thoung.customer_service.application.repository.CustomerRepository;
import co.istad.thoung.customer_service.application.repository.CustomerSegmentRepository;
import co.istad.thoung.customer_service.data.entity.CustomerEntity;
import co.istad.thoung.customer_service.data.entity.CustomerSegmentEntity;
import co.istad.thoung.customer_service.domain.event.CustomerCreatedEvent;
import co.istad.thoung.customer_service.domain.event.CustomerPhoneNumberChangedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
@Slf4j
//@Transactional
@ProcessingGroup("customer-group")
public class CustomerListener {

    private final CustomerRepository customerRepository;
    private final CustomerSegmentRepository customerSegmentRepository;
    private final CustomerApplicationMapper customerApplicationMapper;

    @EventHandler
    public void on(CustomerCreatedEvent customerCreatedEvent) {
        log.info("Customer Created Event {}", customerCreatedEvent);

        CustomerEntity customerEntity = customerApplicationMapper
                .customerCreatedEventToCustomerEntity(customerCreatedEvent);

        customerEntity.getAddress().setCustomer(customerEntity);
        customerEntity.getContact().setCustomer(customerEntity);
        customerEntity.getKyc().setCustomer(customerEntity);

        CustomerSegmentEntity customerSegementEntity = customerSegmentRepository.
                findById(customerCreatedEvent.customerSegmentId().customerSegmentId())
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Segment not found"));

        customerEntity.setCustomerSegment(customerSegementEntity);

        customerRepository.save(customerEntity);
    }

    @EventHandler
    public void on(CustomerPhoneNumberChangedEvent customerPhoneNumberChangedEvent) {
        log.info("on CustomerPhoneNumberChangedEvent {}", customerPhoneNumberChangedEvent);

        CustomerEntity customerEntity = customerRepository.findById(customerPhoneNumberChangedEvent.customerId().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

//        customerApplicationMapper.updatePhoneNumberFromEvent(customerPhoneNumberChangedEvent, customerEntity);
        customerEntity.setPhoneNumber(customerPhoneNumberChangedEvent.phoneNumber());

        customerRepository.save(customerEntity);
    }
}
