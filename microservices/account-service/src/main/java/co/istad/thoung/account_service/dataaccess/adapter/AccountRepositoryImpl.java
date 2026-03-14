package co.istad.thoung.account_service.dataaccess.adapter;

import co.istad.thoung.account_service.application.ports.output.repostitory.AccountRepository;
import co.istad.thoung.account_service.dataaccess.entity.AccountEntity;
import co.istad.thoung.account_service.dataaccess.mapper.AccountDataAccessMapper;
import co.istad.thoung.account_service.dataaccess.mapper.AccountTypeCodeJpaRepository;
import co.istad.thoung.account_service.dataaccess.repository.AccountJpaRepository;
import co.istad.thoung.account_service.domain.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountDataAccessMapper accountDataAccessMapper;
    private final AccountJpaRepository accountJpaRepository;
    private final AccountTypeCodeJpaRepository accountTypeCodeJpaRepository;

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = accountDataAccessMapper.accountToAccountEntity(account);

        if (account.getAccountTypeId() != null) {
            accountTypeCodeJpaRepository
                    .findById(account.getAccountTypeId())
                    .ifPresent(accountEntity::setAccountType);
        }

        return accountDataAccessMapper.accountEntityToAccount(accountJpaRepository.save(accountEntity));
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return accountJpaRepository.findById(id).map(accountDataAccessMapper::accountEntityToAccount);
    }

}
