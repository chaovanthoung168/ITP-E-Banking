package co.istad.thoung.account_service.application.mapper;

import co.istad.thoung.account_service.application.dto.create.CreateAccountRequest;
import co.istad.thoung.account_service.dataaccess.entity.AccountEntity;

import co.istad.thoung.account_service.domain.command.CreateAccountCommand;
import co.istad.thoung.account_service.domain.entity.Account;
import co.istad.thoung.common.domain.event.AccountCreateEvent;
import co.istad.thoung.common.domain.valueobject.AccountId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountAppDataMapper {


    @Mapping(target = "status", ignore = true)
    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "branchId.value", target = "branchId")
    @Mapping(source = "initialBalance", target = "balance")
    Account accountCreateEventToAccount(AccountCreateEvent accountCreateEvent);

    CreateAccountCommand CreateAccountRequestToCreateAccountCommand(AccountId accountId, CreateAccountRequest createAccountRequest);

}
