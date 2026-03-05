package co.istad.thoung.account_service.application.mapper;

import co.istad.thoung.account_service.application.dto.CreateAccountRequest;
import co.istad.thoung.account_service.application.dto.CreateAccountResponse;
import co.istad.thoung.account_service.data.entity.AccountEntity;
import co.istad.thoung.account_service.domain.command.CreateAccountCommand;
import co.istad.thoung.account_service.domain.event.AccountCreateEvent;
import co.istad.thoung.common.valueobject.AccountId;
import co.istad.thoung.common.valueobject.CustomerId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    CreateAccountCommand CreateAccountRequestToCreateAccountCommand(AccountId accountId, CreateAccountRequest createAccountRequest);


    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "branchId.value", target = "branchId")
    @Mapping(source = "initialBalance", target = "balance")
    @Mapping(target = "accountType", ignore = true)   // handled manually in listener
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    AccountEntity accountCreateEventToAccountEntity(AccountCreateEvent accountCreateEvent);
}
