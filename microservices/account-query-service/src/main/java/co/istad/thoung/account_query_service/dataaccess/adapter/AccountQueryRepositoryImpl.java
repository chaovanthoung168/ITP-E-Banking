package co.istad.thoung.account_query_service.dataaccess.adapter;

import co.istad.thoung.account_query_service.applicationservice.ports.output.repository.AccountQueryRepository;
import co.istad.thoung.account_query_service.dataaccess.enity.AccountEntity;
import co.istad.thoung.account_query_service.dataaccess.mapper.AccountDataAccessMapper;
import co.istad.thoung.account_query_service.dataaccess.repository.AccountQueryReactiveRepository;
import co.istad.thoung.account_query_service.domain.Entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountQueryRepositoryImpl implements AccountQueryRepository {

    private final AccountQueryReactiveRepository accountQueryReactiveRepository;
    private final AccountDataAccessMapper accountDataAccessMapper;

    @Override
    public Mono<Account> save(Account account) {

        AccountEntity accountEntity = accountDataAccessMapper.accountToAccountEntity(account);

        return accountQueryReactiveRepository.save(accountEntity)
                .map(accountDataAccessMapper::accountEntityToAccount);
    }

    @Override
    public Mono<Account> findById(UUID id) {

        return accountQueryReactiveRepository.findById(id).map(accountDataAccessMapper::accountEntityToAccount);
    }
}
