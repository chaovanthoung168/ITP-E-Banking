package co.istad.thoung.account_service.data.entity;

import co.istad.thoung.account_service.domain.valueobject.AccountTypeCode;
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
    UUID accountTypeCodeId;

    @Enumerated(EnumType.STRING)
    AccountTypeCode accountTypeCode;



}
