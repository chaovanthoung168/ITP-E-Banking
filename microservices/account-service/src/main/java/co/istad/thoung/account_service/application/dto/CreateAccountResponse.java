package co.istad.thoung.account_service.application.dto;

import co.istad.thoung.account_service.domain.valueobject.AccountTypeCode;
import co.istad.thoung.account_service.domain.valueobject.Money;
import co.istad.thoung.common.valueobject.AccountId;
import co.istad.thoung.common.valueobject.BranchId;
import co.istad.thoung.common.valueobject.CustomerId;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record CreateAccountResponse(
       UUID customerId,
       String message
) {
}
