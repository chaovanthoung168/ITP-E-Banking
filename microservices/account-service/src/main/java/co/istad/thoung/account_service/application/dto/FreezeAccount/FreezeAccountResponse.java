package co.istad.thoung.account_service.application.dto.FreezeAccount;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record FreezeAccountResponse(
        UUID accountId,
        String previousStatus,
        String status,
        String reason,
        String requestBy,
        ZonedDateTime createdAt
) {
}
