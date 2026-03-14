package co.istad.thoung.account_query_service.message.listener;

import co.istad.thoung.account_query_service.applicationservice.ports.input.message.listener.AccountMessageListener;
import co.istad.thoung.common.domain.event.AccountCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ProcessingGroup("account-group")
public class AccountAxonKafkaListener {

    private final AccountMessageListener accountMessageListener;

    @EventHandler
    public void on(AccountCreateEvent accountCreateEvent) {
        log.info("On accountCreatedEvent {}", accountCreateEvent);
        accountMessageListener.onAccountCreatedEvent(accountCreateEvent);
    }


}
