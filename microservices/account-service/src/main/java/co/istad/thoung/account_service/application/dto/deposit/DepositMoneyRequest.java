package co.istad.thoung.account_service.application.dto.deposit;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

// Request
@Builder
public record DepositMoneyRequest(
        BigDecimal amount,
        String currency,
        String remarks
) {}

