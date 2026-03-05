package co.istad.thoung.account_service.domain.event;

import co.istad.thoung.account_service.domain.valueobject.AccountTypeCode;
import co.istad.thoung.account_service.domain.valueobject.Money;
import co.istad.thoung.common.valueobject.AccountId;
import co.istad.thoung.common.valueobject.BranchId;
import co.istad.thoung.common.valueobject.CustomerId;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record AccountCreateEvent(
    AccountId accountId,
    String accountNumber,
    String accountHolder,
    CustomerId customerId,
    AccountTypeCode accountTypeCode,
    BranchId branchId,
    Money initialBalance,
    ZonedDateTime createdAt,
    String createdBy

) {
}
