package co.istad.thoung.account_service.domain.event;

import co.istad.thoung.common.domain.valueobject.AccountStatus;
import co.istad.thoung.common.domain.valueobject.AccountId;
import co.istad.thoung.common.domain.valueobject.CustomerId;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record AccountFrozenEvent(
        AccountId accountId,
        CustomerId customerId,
        AccountStatus previousStatus,
        AccountStatus status,
        String reason,
        String requestBy,
        ZonedDateTime createdAt
) {
}
