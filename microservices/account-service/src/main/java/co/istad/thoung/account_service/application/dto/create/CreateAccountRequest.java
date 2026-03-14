package co.istad.thoung.account_service.application.dto.create;

import co.istad.thoung.common.domain.valueobject.*;


public record CreateAccountRequest(
        String accountNumber,
        String accountHolder,
        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance
) {
}
