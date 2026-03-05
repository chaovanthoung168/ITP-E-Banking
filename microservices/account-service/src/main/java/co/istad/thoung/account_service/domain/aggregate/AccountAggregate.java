package co.istad.thoung.account_service.domain.aggregate;

import co.istad.thoung.account_service.domain.command.CreateAccountCommand;
import co.istad.thoung.account_service.domain.event.AccountCreateEvent;
import co.istad.thoung.account_service.domain.valueobject.AccountStatus;
import co.istad.thoung.account_service.domain.valueobject.AccountTypeCode;
import co.istad.thoung.account_service.domain.valueobject.Money;
import co.istad.thoung.common.valueobject.AccountId;
import co.istad.thoung.common.valueobject.BranchId;
import co.istad.thoung.common.valueobject.CustomerId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.ZonedDateTime;

@Aggregate
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class AccountAggregate {

    @AggregateIdentifier
    AccountId accountId;
    String accountNumber;
    String accountHolder;
    CustomerId customerId;
    AccountTypeCode accountTypeCode;
    BranchId branchId;
    Money balance;
    AccountStatus accountStatus;
    ZonedDateTime createdAt;
    String createdBy;
    ZonedDateTime updatedAt;
    String updatedBy;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){

        AccountCreateEvent accountCreateEvent =  AccountCreateEvent.builder()
                .accountId(createAccountCommand.accountId())
                .accountHolder(createAccountCommand.accountHolder())
                .accountNumber(createAccountCommand.accountNumber())
                .customerId(createAccountCommand.customerId())
                .accountTypeCode(createAccountCommand.accountTypeCode())
                .branchId(createAccountCommand.branchId())
                .initialBalance(createAccountCommand.initialBalance())
                .createdAt(ZonedDateTime.now())
                .createdBy("system")
                .build();

        AggregateLifecycle.apply(accountCreateEvent);
    }

    @EventSourcingHandler
    public void on(AccountCreateEvent event) {
        this.accountId = event.accountId();
        this.accountNumber = event.accountNumber();
        this.accountHolder = event.accountHolder();
        this.customerId = event.customerId();
        this.accountTypeCode = event.accountTypeCode();
        this.branchId = event.branchId();
        this.balance = event.initialBalance();
        this.accountStatus = AccountStatus.ACTIVE; // Set initial status
        this.createdAt = event.createdAt();
        this.createdBy = event.createdBy();
    }

}
