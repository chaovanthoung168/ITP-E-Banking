package co.istad.thoung.customer_service.domain.event;

import co.istad.thoung.common.valueobject.CustomerId;
import lombok.Builder;

@Builder
public record CustomerPhoneNumberChangedEvent(
        CustomerId customerId,
        String phoneNumber
) {
}
