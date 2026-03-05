package co.istad.thoung.account_service.data.entity;


import co.istad.thoung.common.domain.valueobject.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tranctions")
public class TransactionEntity {

    @Id
    UUID transactionId;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    private String remarks;

    @Embedded
    private Money money;
}
