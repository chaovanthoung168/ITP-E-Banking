package co.istad.thoung.account_service.application;

import co.istad.thoung.account_service.application.mapper.AccountAppDataMapper;
import co.istad.thoung.account_service.application.ports.input.message.listener.AccountMessageListener;
import co.istad.thoung.account_service.application.ports.output.repostitory.AccountRepository;
import co.istad.thoung.account_service.application.ports.output.repostitory.AccountTypeCodeRepository;
import co.istad.thoung.account_service.dataaccess.entity.AccountTypeCodeEntity;
import co.istad.thoung.account_service.dataaccess.mapper.AccountDataAccessMapper;
import co.istad.thoung.account_service.domain.entity.Account;
import co.istad.thoung.account_service.domain.event.AccountFrozenEvent;
import co.istad.thoung.account_service.domain.event.MoneyDepositedEvent;
import co.istad.thoung.account_service.domain.event.MoneyWithdrawEvent;
import co.istad.thoung.common.domain.event.AccountCreateEvent;
import co.istad.thoung.common.domain.valueobject.AccountStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
@Slf4j
public class AccountMessageListenerImpl implements AccountMessageListener {

    private final AccountRepository accountRepository;
    private final AccountAppDataMapper accountAppDataMapper;
    private final AccountTypeCodeRepository accountTypeCodeRepository;


    @Override
    @EventHandler
    public void onAccountCreatedEvent(AccountCreateEvent event) {
        Account account = accountAppDataMapper.accountCreateEventToAccount(event);
        account.setStatus(AccountStatus.ACTIVE);

        AccountTypeCodeEntity accountTypeCodeEntity = accountTypeCodeRepository
                .findByCode(event.accountTypeCode())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account type code not found: " + event.accountTypeCode()));

        account.setAccountTypeId(accountTypeCodeEntity.getAccountTypeCodeId());

        accountRepository.save(account);
        log.info("✅ Account saved: {}, Account Type: {}", account.getAccountId(),account.getAccountTypeCode());
    }

    @Override
    @EventHandler
    public void onMoneyDepositedEvent(MoneyDepositedEvent event) {
        Account account = accountRepository.findById(event.accountId().getValue())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));
        account.setBalance(event.newBalance());
        account.setUpdatedAt(event.createdAt());
        accountRepository.save(account);
        log.info("✅ Deposit saved, new balance: {}", event.newBalance());
    }

    @Override
    @EventHandler
    public void onMoneyWithdrawnEvent(MoneyWithdrawEvent event) {
        Account account = accountRepository.findById(event.accountId().getValue())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));
        account.setBalance(event.newBalance());
        account.setUpdatedAt(event.createdAt());
        accountRepository.save(account);
        log.info("✅ Withdraw saved, new balance: {}", event.newBalance());
    }

    @Override
    @EventHandler
    public void onAccountFrozenEvent(AccountFrozenEvent event) {
        Account account = accountRepository.findById(event.accountId().getValue())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));
        account.setStatus(AccountStatus.FREEZE);
        account.setUpdatedAt(event.createdAt());
        accountRepository.save(account);
        log.info("✅ Account frozen: {}", event.accountId().getValue());
    }

}
