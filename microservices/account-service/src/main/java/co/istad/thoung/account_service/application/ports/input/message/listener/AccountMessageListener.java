package co.istad.thoung.account_service.application.ports.input.message.listener;

import co.istad.thoung.account_service.domain.event.AccountFrozenEvent;
import co.istad.thoung.account_service.domain.event.MoneyDepositedEvent;
import co.istad.thoung.account_service.domain.event.MoneyWithdrawEvent;
import co.istad.thoung.common.domain.event.AccountCreateEvent;

public interface AccountMessageListener {

    void onAccountCreatedEvent(AccountCreateEvent accountCreateEvent);
    void onMoneyDepositedEvent(MoneyDepositedEvent event);
    void onMoneyWithdrawnEvent(MoneyWithdrawEvent event);
    void onAccountFrozenEvent(AccountFrozenEvent event);

}
