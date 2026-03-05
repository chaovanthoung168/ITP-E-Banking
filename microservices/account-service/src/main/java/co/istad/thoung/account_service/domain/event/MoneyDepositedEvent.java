package co.istad.thoung.account_service.domain.event;

import co.istad.thoung.common.domain.valueobject.AccountId;
import co.istad.thoung.common.domain.valueobject.CustomerId;
import co.istad.thoung.common.domain.valueobject.Money;
import co.istad.thoung.common.domain.valueobject.TransactionId;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record MoneyDepositedEvent(
        AccountId accountId,
        CustomerId customerId,
        TransactionId transactionId,
        Money amount,
        Money newBalance,
        String remarks,
        ZonedDateTime createdAt
) {
}
