package co.istad.thoung.account_service.application.ports.output.repostitory;

import co.istad.thoung.account_service.domain.entity.Account;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

//    // save
//    Mono<Account> save(Account Account);
//
//    // find by id
//    Mono<Account> findById(UUID id);

        // save
    Account save(Account account);

    // find by id
    Optional<Account> findById(UUID id);

}
