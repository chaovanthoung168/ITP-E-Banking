package co.istad.thoung.customer_service.domain.command;

import co.istad.thoung.common.valueobject.CustomerId;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record ChangePhoneNumberCommand(
        @TargetAggregateIdentifier
        CustomerId customerId,
        String phoneNumber
) {
}
