package co.istad.thoung.account_service.application.dto.deposit;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record DepositMoneyResponse(
        UUID accountId,
        UUID transactionId,
        BigDecimal newBalance,
        String currency,
        String remarks,
        ZonedDateTime createdAt
) {}
