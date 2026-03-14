package co.istad.thoung.account_service.application;

import co.istad.thoung.account_service.application.dto.FreezeAccount.FreezeAccountRequest;
import co.istad.thoung.account_service.application.dto.FreezeAccount.FreezeAccountResponse;
import co.istad.thoung.account_service.application.dto.create.CreateAccountRequest;
import co.istad.thoung.account_service.application.dto.create.CreateAccountResponse;
import co.istad.thoung.account_service.application.dto.deposit.DepositMoneyRequest;
import co.istad.thoung.account_service.application.dto.deposit.DepositMoneyResponse;
import co.istad.thoung.account_service.application.dto.withdraw.WithdrawMoneyRequest;
import co.istad.thoung.account_service.application.dto.withdraw.WithdrawMoneyResponse;
import co.istad.thoung.account_service.application.mapper.AccountAppDataMapper;
//import co.istad.thoung.account_service.application.repository.AccountRepository;
import co.istad.thoung.account_service.application.ports.input.service.AccountService;
import co.istad.thoung.account_service.application.ports.output.repostitory.AccountRepository;
import co.istad.thoung.account_service.application.ports.output.repostitory.AccountTypeCodeRepository;
import co.istad.thoung.account_service.dataaccess.entity.AccountEntity;
import co.istad.thoung.account_service.domain.aggregate.AccountAggregate;
import co.istad.thoung.account_service.domain.command.CreateAccountCommand;

import co.istad.thoung.account_service.domain.command.DepositMoneyCommand;
import co.istad.thoung.account_service.domain.command.FreezeAccountCommand;
import co.istad.thoung.account_service.domain.command.WithdrawMoneyCommand;
import co.istad.thoung.account_service.domain.entity.Account;
import co.istad.thoung.common.domain.valueobject.AccountStatus;
import co.istad.thoung.common.domain.valueobject.AccountId;
import co.istad.thoung.common.domain.valueobject.Currency;
import co.istad.thoung.common.domain.valueobject.Money;
import co.istad.thoung.common.domain.valueobject.TransactionId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final CommandGateway commandGateway;
    private final AccountAppDataMapper accountAppDataMapper;
    private final AccountRepository accountRepository;


    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {

        CreateAccountCommand createAccountCommand = accountAppDataMapper
                .CreateAccountRequestToCreateAccountCommand(new AccountId(UUID.randomUUID()), createAccountRequest);
        log.info("CreateAccountCommand: {}", createAccountCommand);

        AccountId result = commandGateway.sendAndWait(createAccountCommand);
        log.info("Command Gateway result: {}", result);

        return CreateAccountResponse.builder()
                .accountId(createAccountCommand.accountId().getValue())
                .customerId(createAccountCommand.customerId().getValue())
                .message("Account created successfully")
                .build();

    }

    @Override
    public DepositMoneyResponse depositMoney(UUID accountId, DepositMoneyRequest depositMoneyRequest) {

        DepositMoneyCommand depositMoneyCommand = DepositMoneyCommand.builder()
                .accountId(new AccountId(accountId))
                .transactionId(new TransactionId(UUID.randomUUID()))
                .amount(new Money(BigDecimal.valueOf(depositMoneyRequest.amount().doubleValue()),
                        Currency.valueOf(depositMoneyRequest.currency())))
                .remarks(depositMoneyRequest.remarks())
                .build();

        log.info("DepositMoneyCommand: {}", depositMoneyCommand);
        commandGateway.sendAndWait(depositMoneyCommand);

        return DepositMoneyResponse.builder()
                .accountId(depositMoneyCommand.accountId().getValue())
                .transactionId(depositMoneyCommand.transactionId().value())
                .newBalance(depositMoneyCommand.amount().amount())
                .currency(depositMoneyRequest.currency())
                .remarks(depositMoneyCommand.remarks())
                .createdAt(ZonedDateTime.now())
                .build();
    }

    @Override
    public WithdrawMoneyResponse withdrawMoney(UUID accountId, WithdrawMoneyRequest withdrawMoneyRequest) {

        WithdrawMoneyCommand withdrawMoneyCommand = WithdrawMoneyCommand.builder()
                .accountId(new AccountId(accountId))
                .transactionId(new TransactionId(UUID.randomUUID()))
                .amount(new Money(BigDecimal.valueOf(withdrawMoneyRequest.amount().doubleValue()),
                        Currency.valueOf(withdrawMoneyRequest.currency())))
                .remarks(withdrawMoneyRequest.remarks())
                .build();

        log.info("DepositMoneyCommand: {}", withdrawMoneyCommand);
        commandGateway.sendAndWait(withdrawMoneyCommand);

        return WithdrawMoneyResponse.builder()
                .accountId(withdrawMoneyCommand.accountId().getValue())
                .transactionId(withdrawMoneyCommand.transactionId().value())
                .newBalance(withdrawMoneyCommand.amount().amount())
                .currency(withdrawMoneyRequest.currency())
                .remarks(withdrawMoneyCommand.remarks())
                .createdAt(ZonedDateTime.now())
                .build();
    }

    @Override
    public FreezeAccountResponse freezeAccount(UUID accountId, FreezeAccountRequest freezeAccountRequest) {

        Account account = accountRepository.findById(accountId)  // ✅ Account not AccountEntity
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));

        AccountStatus previousStatus = account.getStatus();  // ✅ getStatus() from Account

        FreezeAccountCommand command = FreezeAccountCommand.builder()
                .accountId(new AccountId(accountId))
                .remarks(freezeAccountRequest.reason())
                .requestedBy(freezeAccountRequest.requestBy())
                .build();

        log.info("FreezeAccountCommand: {}", command);
        commandGateway.sendAndWait(command);

        return FreezeAccountResponse.builder()
                .accountId(accountId)
                .previousStatus(previousStatus.name())
                .status(AccountStatus.FREEZE.name())
                .reason(freezeAccountRequest.reason())
                .requestBy(freezeAccountRequest.requestBy())
                .createdAt(ZonedDateTime.now())
                .build();
    }

}
