package co.istad.thoung.customer_service.application.dto.query;

import co.istad.thoung.common.valueobject.CustomerSegmentId;
import co.istad.thoung.customer_service.domain.valueobject.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerResponse(
        UUID customerId,
        @NotNull
        CustomerName name,
        @NotNull
        CustomerGender gender,
        @NotNull
        CustomerEmail email,
        @NotNull
        LocalDate dob,
        @NotNull
        Kyc kyc,
        @NotNull
        Address address,
        @NotNull
        Contact contact,
        @NotNull
        CustomerSegmentResponse customerSegment,
        @NotNull
        String phoneNumber
) {
}
