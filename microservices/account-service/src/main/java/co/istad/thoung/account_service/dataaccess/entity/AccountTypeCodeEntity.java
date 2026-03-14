package co.istad.thoung.account_service.dataaccess.entity;

import co.istad.thoung.common.domain.valueobject.AccountTypeCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts_type")
public class AccountTypeCodeEntity {

    @Id
    private UUID accountTypeCodeId;

    @Enumerated(EnumType.STRING)
    private AccountTypeCode accountTypeCode;



}
