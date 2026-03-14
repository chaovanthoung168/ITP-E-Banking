package co.istad.thoung.account_service.dataaccess.mapper;

import co.istad.thoung.account_service.dataaccess.entity.AccountEntity;
import co.istad.thoung.account_service.domain.aggregate.AccountAggregate;
import co.istad.thoung.account_service.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDataAccessMapper {

    @Mapping(source = "balance", target = "balance")
    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    AccountEntity accountToAccountEntity(Account account);

    Account accountEntityToAccount(AccountEntity accountEntity);

}
