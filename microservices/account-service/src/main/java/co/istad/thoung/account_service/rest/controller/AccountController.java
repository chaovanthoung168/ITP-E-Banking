package co.istad.thoung.account_service.rest.controller;


import co.istad.thoung.account_service.application.dto.FreezeAccount.FreezeAccountRequest;
import co.istad.thoung.account_service.application.dto.FreezeAccount.FreezeAccountResponse;
import co.istad.thoung.account_service.application.dto.create.CreateAccountRequest;
import co.istad.thoung.account_service.application.dto.create.CreateAccountResponse;
import co.istad.thoung.account_service.application.dto.deposit.DepositMoneyRequest;
import co.istad.thoung.account_service.application.dto.deposit.DepositMoneyResponse;
import co.istad.thoung.account_service.application.dto.withdraw.WithdrawMoneyRequest;
import co.istad.thoung.account_service.application.dto.withdraw.WithdrawMoneyResponse;
import co.istad.thoung.account_service.application.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @PostMapping("/{accountId}/deposit")
    public DepositMoneyResponse depositMoney(
            @PathVariable UUID accountId,
            @RequestBody DepositMoneyRequest depositMoneyRequest) {
        return accountService.depositMoney(accountId, depositMoneyRequest);
    }

    @PostMapping("/{accountId}/withdraw")
    public WithdrawMoneyResponse depositMoney(
            @PathVariable UUID accountId,
            @RequestBody WithdrawMoneyRequest withdrawMoneyRequest) {
        return accountService.withdrawMoney(accountId, withdrawMoneyRequest);
    }

    @PatchMapping("/{accountId}/freeze")
    public FreezeAccountResponse freezeAccount(
            @PathVariable UUID accountId,
            @RequestBody FreezeAccountRequest request) {
        return accountService.freezeAccount(accountId, request);
    }
}
