package co.istad.thoung.account_service.application.service;

import co.istad.thoung.account_service.application.dto.FreezeAccount.FreezeAccountRequest;
import co.istad.thoung.account_service.application.dto.FreezeAccount.FreezeAccountResponse;
import co.istad.thoung.account_service.application.dto.create.CreateAccountRequest;
import co.istad.thoung.account_service.application.dto.create.CreateAccountResponse;
import co.istad.thoung.account_service.application.dto.deposit.DepositMoneyRequest;
import co.istad.thoung.account_service.application.dto.deposit.DepositMoneyResponse;
import co.istad.thoung.account_service.application.dto.withdraw.WithdrawMoneyRequest;
import co.istad.thoung.account_service.application.dto.withdraw.WithdrawMoneyResponse;

import java.util.UUID;

public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    // deposit
    DepositMoneyResponse depositMoney(UUID accountId, DepositMoneyRequest depositMoneyRequest);
    // withdraw
    WithdrawMoneyResponse withdrawMoney(UUID accountId, WithdrawMoneyRequest withdrawMoneyRequest );
    // freeze account
    FreezeAccountResponse freezeAccount(UUID accountId, FreezeAccountRequest freezeAccountRequest);

}
