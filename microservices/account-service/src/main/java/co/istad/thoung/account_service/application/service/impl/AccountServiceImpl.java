package co.istad.thoung.account_service.application.service.impl;

import co.istad.thoung.account_service.application.dto.CreateAccountRequest;
import co.istad.thoung.account_service.application.dto.CreateAccountResponse;
import co.istad.thoung.account_service.application.mapper.AccountMapper;
import co.istad.thoung.account_service.application.service.AccountService;
import co.istad.thoung.account_service.domain.command.CreateAccountCommand;
import co.istad.thoung.common.valueobject.AccountId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final CommandGateway commandGateway;
    private final AccountMapper accountMapper;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {

        CreateAccountCommand createAccountCommand = accountMapper
                .CreateAccountRequestToCreateAccountCommand(new AccountId(UUID.randomUUID()), createAccountRequest);
        log.info("CreateAccountCommand: {}", createAccountCommand);

        AccountId result = commandGateway.sendAndWait(createAccountCommand);
        log.info("Command Gateway result: {}", result);

        return CreateAccountResponse.builder()
                .customerId(createAccountCommand.customerId().getValue())
                .message("Account created successfully")
                .build();

    }

}
