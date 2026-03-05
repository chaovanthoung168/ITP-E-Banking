package co.istad.thoung.account_service.data.entity;

import co.istad.thoung.account_service.domain.valueobject.AccountStatus;
import co.istad.thoung.common.domain.valueobject.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID accountId;

    private UUID customerId;
    private UUID branchId;
    private String accountNumber;
    private String accountHolder;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Embedded
    private Money balance;

//    @Enumerated(EnumType.STRING)
//    private Currency currency;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;


    @ManyToOne()
    @JoinColumn(name = "account_type_id")
    private AccountTypeCodeEntity accountType;

}
