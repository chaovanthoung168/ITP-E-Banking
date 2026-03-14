package co.istad.thoung.account_query_service.applicationservice;

import co.istad.thoung.account_query_service.applicationservice.mapper.AccountAppDataMapper;
import co.istad.thoung.account_query_service.applicationservice.ports.input.message.listener.AccountMessageListener;
import co.istad.thoung.account_query_service.applicationservice.ports.output.repository.AccountQueryRepository;
import co.istad.thoung.account_query_service.dataaccess.mapper.AccountDataAccessMapper;
import co.istad.thoung.account_query_service.domain.Entity.Account;
import co.istad.thoung.common.domain.event.AccountCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountMessageListenerImpl implements AccountMessageListener {

    private final AccountQueryRepository accountQueryRepository;
    private final AccountAppDataMapper accountAppDataMapper;

    @Override
    public void onAccountCreatedEvent(AccountCreateEvent accountCreateEvent) {

        Account account = accountAppDataMapper.accountCreatedEventToAccount(accountCreateEvent);
        accountQueryRepository.save(account)
                .doOnSuccess(data -> log.info("Save Account Id = {} successfully",accountCreateEvent.accountId()))
                .subscribe();

    }
}
