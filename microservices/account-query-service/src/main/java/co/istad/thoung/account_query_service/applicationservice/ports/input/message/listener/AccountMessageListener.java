package co.istad.thoung.account_query_service.applicationservice.ports.input.message.listener;

import co.istad.thoung.common.domain.event.AccountCreateEvent;

public interface AccountMessageListener {

    void onAccountCreatedEvent(AccountCreateEvent accountCreateEvent);

}
