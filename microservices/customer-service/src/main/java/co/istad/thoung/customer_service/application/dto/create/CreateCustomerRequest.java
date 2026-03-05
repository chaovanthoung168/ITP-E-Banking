package co.istad.thoung.customer_service.application.dto.create;

import co.istad.thoung.common.domain.valueobject.CustomerSegmentId;
import co.istad.thoung.customer_service.domain.valueobject.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateCustomerRequest(
        CustomerName name,
        CustomerGender gender,
        CustomerEmail email,
        LocalDate dob,
        Kyc kyc,
        Address address,
        Contact contact,
        CustomerSegmentId customerSegmentId,
        String phoneNumber
) {
}
