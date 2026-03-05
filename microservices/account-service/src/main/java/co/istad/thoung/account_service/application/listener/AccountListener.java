package co.istad.thoung.account_service.application.listener;

import co.istad.thoung.account_service.application.mapper.AccountMapper;
import co.istad.thoung.account_service.application.repository.AccountRepository;
import co.istad.thoung.account_service.data.entity.AccountEntity;
import co.istad.thoung.account_service.domain.event.AccountCreateEvent;
import co.istad.thoung.account_service.domain.valueobject.AccountStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountListener {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @EventHandler
    public void on (AccountCreateEvent accountCreateEvent) {
        log.info("AccountCreateEvent: {}", accountCreateEvent);

        AccountEntity accountEntity = accountMapper
                .accountCreateEventToAccountEntity(accountCreateEvent);

        accountEntity.setStatus(AccountStatus.ACTIVE);

        accountRepository.save(accountEntity);

        log.info("✅ Account saved to DB: {}", accountEntity.getAccountId());

    }

}
