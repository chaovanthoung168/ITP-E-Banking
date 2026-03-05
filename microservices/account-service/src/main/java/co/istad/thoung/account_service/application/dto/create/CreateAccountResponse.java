package co.istad.thoung.account_service.application.dto.create;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateAccountResponse(
        UUID accountId,
       UUID customerId,
       String message
) {
}
