package co.istad.thoung.account_service.domain.command;

import co.istad.thoung.common.domain.valueobject.AccountId;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record FreezeAccountCommand(
        @TargetAggregateIdentifier
         AccountId accountId,
         String remarks,
         String requestedBy
) {
}
