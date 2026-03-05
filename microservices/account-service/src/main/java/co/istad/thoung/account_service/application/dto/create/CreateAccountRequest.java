package co.istad.thoung.account_service.application.dto.create;

import co.istad.thoung.account_service.domain.valueobject.AccountTypeCode;

import co.istad.thoung.common.domain.valueobject.BranchId;
import co.istad.thoung.common.domain.valueobject.CustomerId;
import co.istad.thoung.common.domain.valueobject.Money;


public record CreateAccountRequest(
        String accountNumber,
        String accountHolder,
        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance
) {
}
