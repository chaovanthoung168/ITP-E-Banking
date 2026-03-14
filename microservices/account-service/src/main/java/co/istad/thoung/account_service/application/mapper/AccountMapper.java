package co.istad.thoung.account_service.application.mapper;

import co.istad.thoung.account_service.application.dto.create.CreateAccountRequest;
import co.istad.thoung.account_service.data.entity.AccountEntity;

import co.istad.thoung.account_service.domain.command.CreateAccountCommand;
import co.istad.thoung.common.domain.event.AccountCreateEvent;
import co.istad.thoung.common.domain.valueobject.AccountId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    CreateAccountCommand CreateAccountRequestToCreateAccountCommand(AccountId accountId, CreateAccountRequest createAccountRequest);


    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "branchId.value", target = "branchId")
    @Mapping(source = "initialBalance", target = "balance")
    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    AccountEntity accountCreateEventToAccountEntity(AccountCreateEvent accountCreateEvent);
}
