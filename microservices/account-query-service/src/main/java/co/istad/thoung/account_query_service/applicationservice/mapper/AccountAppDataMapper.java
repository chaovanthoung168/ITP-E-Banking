package co.istad.thoung.account_query_service.applicationservice.mapper;

import co.istad.thoung.account_query_service.applicationservice.dto.AccountQueryResponse;
import co.istad.thoung.account_query_service.domain.Entity.Account;
import co.istad.thoung.common.domain.event.AccountCreateEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountAppDataMapper {

    AccountQueryResponse accountToAccountQueryResponse(Account account);

    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "branchId.value", target = "branchId")
    Account accountCreatedEventToAccount(AccountCreateEvent accountCreateEvent);

}
