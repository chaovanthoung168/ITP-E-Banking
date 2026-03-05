package co.istad.thoung.account_service.application.dto.withdraw;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WithdrawMoneyRequest(
        BigDecimal amount,
        String currency,
        String remarks
) {
}
