package co.istad.thoung.customer_service.application.dto.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ChangePhoneNumberRequest(
        @NotBlank
        String phoneNumber
) {
}
