package co.istad.thoung.account_service.application.listener;

import co.istad.thoung.account_service.application.mapper.AccountMapper;
import co.istad.thoung.account_service.application.repository.AccountRepository;
import co.istad.thoung.account_service.application.repository.AccountTypeCodeRepository;
import co.istad.thoung.account_service.application.repository.TransactionRepository;
import co.istad.thoung.account_service.data.entity.AccountEntity;
import co.istad.thoung.account_service.data.entity.AccountTypeCodeEntity;
import co.istad.thoung.account_service.data.entity.TransactionEntity;
import co.istad.thoung.common.domain.event.AccountCreateEvent;
import co.istad.thoung.account_service.domain.event.AccountFrozenEvent;
import co.istad.thoung.account_service.domain.event.MoneyDepositedEvent;
import co.istad.thoung.account_service.domain.event.MoneyWithdrawEvent;
import co.istad.thoung.common.domain.valueobject.AccountStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountListener {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountTypeCodeRepository accountTypeCodeRepository;
    private final TransactionRepository transactionRepository;

    @EventHandler
    public void on (AccountCreateEvent accountCreateEvent) {
        log.info("AccountCreateEvent: {}", accountCreateEvent);

        AccountEntity accountEntity = accountMapper
                .accountCreateEventToAccountEntity(accountCreateEvent);

        AccountTypeCodeEntity accountTypeCodeEntity = accountTypeCodeRepository
                .findByAccountTypeCode(accountCreateEvent.accountTypeCode())
                        .orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"account type code not found")
                        );

        accountEntity.setStatus(AccountStatus.ACTIVE);
        accountEntity.setAccountType(accountTypeCodeEntity);

        accountRepository.save(accountEntity);

        log.info("✅ Account saved to DB: {}", accountEntity.getAccountId());

    }

    @EventHandler
    public void on (MoneyDepositedEvent moneyDepositedEvent){
        log.info("MoneyDepositedEvent: {}", moneyDepositedEvent);

        AccountEntity accountEntity = accountRepository.findById(moneyDepositedEvent.accountId().getValue())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"account not found")
                );
        accountEntity.setBalance(moneyDepositedEvent.newBalance());
        accountEntity.setUpdatedAt(moneyDepositedEvent.createdAt());

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(moneyDepositedEvent.transactionId().value());
        transactionEntity.setAccount(accountEntity);
        transactionEntity.setRemarks(moneyDepositedEvent.remarks());
        transactionEntity.setMoney(moneyDepositedEvent.amount());

        transactionRepository.save(transactionEntity);
        accountRepository.save(accountEntity);

        log.info("✅ Deposit saved to DB, new balance: {}", moneyDepositedEvent.newBalance());
    }

    @EventHandler
    public void on (MoneyWithdrawEvent moneyWithdrawEvent){
        log.info("MoneyWithdrawEvent: {}", moneyWithdrawEvent);

        AccountEntity accountEntity = accountRepository.findById(moneyWithdrawEvent.accountId().getValue())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"account not found")
                );
        accountEntity.setBalance(moneyWithdrawEvent.newBalance());
        accountEntity.setUpdatedAt(moneyWithdrawEvent.createdAt());

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(moneyWithdrawEvent.transactionId().value());
        transactionEntity.setAccount(accountEntity);
        transactionEntity.setRemarks(moneyWithdrawEvent.remarks());
        transactionEntity.setMoney(moneyWithdrawEvent.amount());

        transactionRepository.save(transactionEntity);
        accountRepository.save(accountEntity);

        log.info("✅ Deposit saved to DB, new balance: {}", moneyWithdrawEvent.newBalance());
    }

    @EventHandler
    public void on(AccountFrozenEvent event) {
        log.info("AccountFrozenEvent: {}", event);

        AccountEntity accountEntity = accountRepository.findById(event.accountId().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        accountEntity.setStatus(AccountStatus.FREEZE);
        accountEntity.setUpdatedAt(event.createdAt());

        accountRepository.save(accountEntity);

        log.info("✅ Account frozen: {}", event.accountId().getValue());
    }

}
