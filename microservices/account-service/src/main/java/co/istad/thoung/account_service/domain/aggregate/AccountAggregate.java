package co.istad.thoung.account_service.domain.aggregate;

import co.istad.thoung.account_service.domain.command.CreateAccountCommand;
import co.istad.thoung.account_service.domain.command.DepositMoneyCommand;
import co.istad.thoung.account_service.domain.command.FreezeAccountCommand;
import co.istad.thoung.account_service.domain.command.WithdrawMoneyCommand;
import co.istad.thoung.common.domain.event.AccountCreateEvent;
import co.istad.thoung.account_service.domain.event.AccountFrozenEvent;
import co.istad.thoung.account_service.domain.event.MoneyDepositedEvent;
import co.istad.thoung.account_service.domain.event.MoneyWithdrawEvent;
import co.istad.thoung.account_service.domain.exception.AccountDomainException;
import co.istad.thoung.account_service.domain.validate.AccountValidate;
import co.istad.thoung.common.domain.valueobject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Slf4j
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


    private void validateAccountType(AccountTypeCode accountTypeCode) {
        if (accountTypeCode != AccountTypeCode.SAVING){
            throw new AccountDomainException("Account type can be only SAVING");
        }
    }

    private void validateInitialBalance(Money initialBalance) {
        Money minInitialBalance = new Money(BigDecimal.valueOf(10), Currency.USD);
        if (initialBalance.isLessThen(minInitialBalance)){
            throw new AccountDomainException("Create new account is required 10$");
        }
    }

    // validation deposit amount
    private void validateDepositAmount(Money amount) {
        Money zero = new Money(BigDecimal.ZERO, amount.currency());
        if (amount.isLessThenOrEqual(zero)) {
            throw new AccountDomainException("Deposit amount must be greater than 0");
        }
    }

    // validation withdraw amount
    private void validateWithdrawAmount(Money amount, Money currentBalance) {
        Money zero = new Money(BigDecimal.ZERO, amount.currency());
        if (amount.isLessThenOrEqual(zero)) {          // uses isLessThenOrEqual ✅
            throw new AccountDomainException("Withdrawal amount must be greater than 0");
        }
        if (amount.isGreaterThen(currentBalance)) {    // uses isGreaterThen ✅
            throw new AccountDomainException("Insufficient balance. Current balance: "
                    + currentBalance.amount());
        }
    }

    // validation account frozen
    private void validateAccountNotAlreadyFrozen(AccountStatus currentStatus) {
        if (currentStatus == AccountStatus.FREEZE) {
            throw new AccountDomainException("Account is already frozen");
        }
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){

        log.info("Aggregate Create AccountCommand {}", createAccountCommand);

        // validate account number
        AccountValidate.validateAccountNumber(createAccountCommand.accountNumber());
        // validate account type code
        validateAccountType(createAccountCommand.accountTypeCode());
        // validate balance
        validateInitialBalance(createAccountCommand.initialBalance());

        // create event object
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

    @CommandHandler
    public void handle(DepositMoneyCommand depositMoneyCommand){

        log.info("Aggregate Deposit MoneyCommand {}", depositMoneyCommand);

        // validation deposit amount
        validateDepositAmount(depositMoneyCommand.amount());

        Money newBalance = this.balance.add(depositMoneyCommand.amount());

        MoneyDepositedEvent moneyDepositedEvent = MoneyDepositedEvent.builder()
                .accountId(depositMoneyCommand.accountId())
                .customerId(this.customerId)
                .transactionId(depositMoneyCommand.transactionId())
                .amount(depositMoneyCommand.amount())
                .newBalance(newBalance)
                .remarks(depositMoneyCommand.remarks())
                .createdAt(ZonedDateTime.now())
                .build();

        AggregateLifecycle.apply(moneyDepositedEvent);
    }

    @CommandHandler
    public void handle(FreezeAccountCommand command) {

        // ✅ validate account is not already frozen
        validateAccountNotAlreadyFrozen(this.accountStatus);

        AccountFrozenEvent accountFrozenEvent = AccountFrozenEvent.builder()
                .accountId(command.accountId())
                .customerId(this.customerId)
                .previousStatus(this.accountStatus)
                .status(AccountStatus.FREEZE)
                .reason(command.remarks())
                .requestBy(command.requestedBy())
                .createdAt(ZonedDateTime.now())
                .build();

        AggregateLifecycle.apply(accountFrozenEvent);
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand withdrawMoneyCommand){

        log.info("Aggregate WithdrawMoneyCommand {}", withdrawMoneyCommand);

        // validation withdraw
        validateWithdrawAmount(withdrawMoneyCommand.amount(), balance);

        Money newBalance = this.balance.subtract(withdrawMoneyCommand.amount());

        MoneyDepositedEvent moneyDepositedEvent = MoneyDepositedEvent.builder()
                .accountId(withdrawMoneyCommand.accountId())
                .customerId(this.customerId)
                .transactionId(withdrawMoneyCommand.transactionId())
                .amount(withdrawMoneyCommand.amount())
                .newBalance(newBalance)
                .remarks(withdrawMoneyCommand.remarks())
                .createdAt(ZonedDateTime.now())
                .build();

        AggregateLifecycle.apply(moneyDepositedEvent);
    }

    // apply aggregate lifecycle
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

    @EventSourcingHandler
    public void on(MoneyDepositedEvent event) {
        this.balance = event.newBalance();
        this.updatedAt = event.createdAt();
    }

    @EventSourcingHandler
    public void on(MoneyWithdrawEvent event) {
        this.balance = event.newBalance();
        this.updatedAt = event.createdAt();
    }

    @EventSourcingHandler
    public void on(AccountFrozenEvent event) {
        this.accountStatus = event.status();
        this.updatedAt = event.createdAt();
    }

}
