package co.istad.thoung.account_query_service.dataaccess.mapper;

import co.istad.thoung.account_query_service.dataaccess.enity.AccountEntity;
import co.istad.thoung.account_query_service.domain.Entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AccountDataAccessMapper {

    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "money.amount", target = "balance")
    @Mapping(source = "money.currency", target = "currency")
    @Mapping(source = "accountTypeId", target = "accountTypeId")
    @Mapping(target = "newEntity", ignore = true)
    AccountEntity accountToAccountEntity(Account account);

    Account accountEntityToAccount(AccountEntity accountEntity);
}
