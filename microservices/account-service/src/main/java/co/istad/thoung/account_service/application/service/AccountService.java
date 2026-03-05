package co.istad.thoung.account_service.application.service;

import co.istad.thoung.account_service.application.dto.CreateAccountRequest;
import co.istad.thoung.account_service.application.dto.CreateAccountResponse;

public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

}
