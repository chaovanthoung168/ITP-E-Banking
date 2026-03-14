package co.istad.thoung.account_service.domain.command;

import co.istad.thoung.common.domain.valueobject.*;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record CreateAccountCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        String accountNumber,
        String accountHolder,
        CustomerId customerId,
//        AccountTypeId accountTypeId,
        BranchId branchId,
        Money initialBalance,
        AccountTypeCode accountTypeCode
) {

}
