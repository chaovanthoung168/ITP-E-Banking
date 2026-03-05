package co.istad.thoung.account_service.domain.valueobject;

import java.math.BigDecimal;

import java.util.UUID;

public record Money(
        UUID moneyId,
        BigDecimal amount,
        Currency currency
) {
}
