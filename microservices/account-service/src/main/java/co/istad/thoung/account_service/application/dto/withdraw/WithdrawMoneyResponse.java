package co.istad.thoung.account_service.application.dto.withdraw;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record WithdrawMoneyResponse(
        UUID accountId,
        UUID transactionId,
        BigDecimal newBalance,
        String currency,
        String remarks,
        ZonedDateTime createdAt
) {
}
