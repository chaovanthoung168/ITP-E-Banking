package co.istad.thoung.account_service.domain.validate;

import co.istad.thoung.account_service.domain.exception.AccountDomainException;

public class AccountValidate {

    public static void validateAccountNumber(String accountNumber) {

        if (accountNumber == null){
            throw new AccountDomainException("Account number cannot be null");
        }

        if (!accountNumber.matches("^\\d{9}$")){
            throw new AccountDomainException("Account number is not valid");
        }

    }

}
