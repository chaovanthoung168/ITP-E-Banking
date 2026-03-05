package co.istad.thoung.customer_service.application.dto.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ChangePhoneNumberResponse(
        @NotBlank
        UUID customerId,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String message
) {
}
