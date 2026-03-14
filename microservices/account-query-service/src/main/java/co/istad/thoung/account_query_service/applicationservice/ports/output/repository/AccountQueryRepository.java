package co.istad.thoung.account_query_service.applicationservice.ports.output.repository;

import co.istad.thoung.account_query_service.domain.Entity.Account;
import reactor.core.publisher.Mono;

import java.util.UUID;

// Output port for data access technologies
public interface AccountQueryRepository {

    // Save account
    Mono<Account> save(Account account);

    Mono<Account> findById(UUID id);
}
