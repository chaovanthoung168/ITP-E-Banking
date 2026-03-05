package co.istad.thoung.customer_service.application.mapper;

import co.istad.thoung.common.valueobject.CustomerId;
import co.istad.thoung.customer_service.application.dto.create.CreateCustomerRequest;
import co.istad.thoung.customer_service.application.dto.query.CustomerResponse;
import co.istad.thoung.customer_service.application.dto.update.ChangePhoneNumberRequest;
import co.istad.thoung.customer_service.data.entity.CustomerEntity;
import co.istad.thoung.customer_service.domain.command.ChangePhoneNumberCommand;
import co.istad.thoung.customer_service.domain.command.CreateCustomerCommand;
import co.istad.thoung.customer_service.domain.event.CustomerCreatedEvent;
import co.istad.thoung.customer_service.domain.event.CustomerPhoneNumberChangedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "Spring")
public interface CustomerApplicationMapper {

    CustomerResponse customerEntityToCustomerResponse(CustomerEntity customerEntity);

    CreateCustomerCommand createCustomerRequestToCreateCustomerCommand(CustomerId customerId, CreateCustomerRequest createCustomerRequest);

    @Mapping(source = "customerId.value", target = "customerId")
    CustomerEntity customerCreatedEventToCustomerEntity(CustomerCreatedEvent customerCreatedEvent);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    void updatePhoneNumberFromEvent(
            CustomerPhoneNumberChangedEvent event,
            @MappingTarget CustomerEntity customerEntity
    );


}
