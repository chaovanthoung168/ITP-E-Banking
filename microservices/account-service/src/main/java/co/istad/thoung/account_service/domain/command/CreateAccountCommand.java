package co.istad.thoung.account_service.domain.command;

import co.istad.thoung.account_service.domain.valueobject.AccountTypeCode;
import co.istad.thoung.common.domain.valueobject.AccountId;
import co.istad.thoung.common.domain.valueobject.BranchId;
import co.istad.thoung.common.domain.valueobject.CustomerId;
import co.istad.thoung.common.domain.valueobject.Money;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record CreateAccountCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        String accountNumber,
        String accountHolder,
        CustomerId customerId,
        AccountTypeCode accountTypeCode,
        BranchId branchId,
        Money initialBalance
) {

}
