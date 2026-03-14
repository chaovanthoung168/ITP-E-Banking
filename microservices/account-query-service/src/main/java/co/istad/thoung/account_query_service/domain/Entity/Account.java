package co.istad.thoung.account_query_service.domain.Entity;

import co.istad.thoung.common.domain.valueobject.AccountStatus;
import co.istad.thoung.common.domain.valueobject.Currency;
import co.istad.thoung.common.domain.valueobject.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Account {

    private UUID accountId;

    private UUID customerId;
    private UUID branchId;
    private String accountNumber;
    private String accountHolder;

    private AccountStatus status;

    private Money money;

    private UUID accountTypeId ;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;

}
