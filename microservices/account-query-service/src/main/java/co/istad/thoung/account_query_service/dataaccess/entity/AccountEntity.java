package co.istad.thoung.account_query_service.dataaccess.entity;


import co.istad.thoung.common.domain.valueobject.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import co.istad.thoung.common.domain.valueobject.AccountStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class AccountEntity implements Persistable<UUID> {

    @Id
    private UUID accountId;

    private UUID customerId;
    private UUID branchId;
    private String accountNumber;
    private String accountHolder;

    private AccountStatus status;


    private BigDecimal balance;
    private Currency currency;

    private UUID accountTypeId ;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;

    @Transient
    private boolean newEntity = true;

    @Override
    public UUID getId() {
        return accountId;
    }

    @Override
    public boolean isNew() {
        return newEntity;
    }

    public AccountEntity markPersisted() {
        this.newEntity = false;
        return this;
    }
}
