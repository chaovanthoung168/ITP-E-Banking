package co.istad.thoung.account_service.domain.command;

import co.istad.thoung.common.domain.valueobject.AccountId;
import co.istad.thoung.common.domain.valueobject.Money;
import co.istad.thoung.common.domain.valueobject.TransactionId;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record DepositMoneyCommand(
        @TargetAggregateIdentifier
        AccountId accountId,
        TransactionId transactionId,
        Money amount,
        String remarks
) {
}
