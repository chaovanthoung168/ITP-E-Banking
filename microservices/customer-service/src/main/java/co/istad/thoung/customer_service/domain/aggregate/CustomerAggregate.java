package co.istad.thoung.customer_service.domain.aggregate;

import co.istad.thoung.common.valueobject.CustomerId;
import co.istad.thoung.common.valueobject.CustomerSegmentId;
import co.istad.thoung.customer_service.domain.command.ChangePhoneNumberCommand;
import co.istad.thoung.customer_service.domain.command.CreateCustomerCommand;
import co.istad.thoung.customer_service.domain.event.CustomerCreatedEvent;
import co.istad.thoung.customer_service.domain.event.CustomerPhoneNumberChangedEvent;
import co.istad.thoung.customer_service.domain.valueobject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Aggregate
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class CustomerAggregate {

    @AggregateIdentifier
    private CustomerId customerId;

    private CustomerName name;
    private CustomerGender gender;
    private CustomerEmail email;
    private LocalDate dob;
    private Kyc kyc;
    private Address address;
    private String phoneNumber;
    private Contact contact;
    private CustomerSegmentId  customerSegmentId;


    private List<String> failureMessages;

    // Domain Login for creating customer
    // Constructor commandHandler required for first event
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand createCustomerCommand){
        // Perform domain logic
        // Validation email
        // Validation phone number

        // Public event -> CustomerCreatedEvent
        CustomerCreatedEvent customerCreatedEvent =
                CustomerCreatedEvent.builder()
                        .customerId(createCustomerCommand.customerId())
                        .name(createCustomerCommand.name())
                        .gender(createCustomerCommand.gender())
                        .email(createCustomerCommand.email())
                        .dob(createCustomerCommand.dob())
                        .kyc(createCustomerCommand.kyc())
                        .address(createCustomerCommand.address())
                        .contact(createCustomerCommand.contact())
                        .customerSegmentId(createCustomerCommand.customerSegmentId())
                        .phoneNumber(createCustomerCommand.phoneNumber())
                        .build();

        AggregateLifecycle.apply(customerCreatedEvent);
    }

    @CommandHandler
    public void handle(ChangePhoneNumberCommand changePhoneNumberCommand){
        CustomerPhoneNumberChangedEvent customerPhoneNumberChangedEvent = CustomerPhoneNumberChangedEvent.builder()
                .customerId(changePhoneNumberCommand.customerId())
                .phoneNumber(changePhoneNumberCommand.phoneNumber())
                .build();
        AggregateLifecycle.apply(customerPhoneNumberChangedEvent);
    }


    @EventSourcingHandler
    public void on(CustomerCreatedEvent customerCreatedEvent) {
        this.customerId = customerCreatedEvent.customerId();
        this.name = customerCreatedEvent.name();
        this.gender = customerCreatedEvent.gender();
        this.email = customerCreatedEvent.email();
        this.dob = customerCreatedEvent.dob();
        this.kyc = customerCreatedEvent.kyc();
        this.address = customerCreatedEvent.address();
        this.contact = customerCreatedEvent.contact();
        this.customerSegmentId = customerCreatedEvent.customerSegmentId();
        this.phoneNumber = customerCreatedEvent.phoneNumber();
    }

    @EventSourcingHandler
    public void on(CustomerPhoneNumberChangedEvent customerPhoneNumberChangedEvent){
        this.phoneNumber = customerPhoneNumberChangedEvent.phoneNumber();
        this.customerId = customerPhoneNumberChangedEvent.customerId();
    }
}
