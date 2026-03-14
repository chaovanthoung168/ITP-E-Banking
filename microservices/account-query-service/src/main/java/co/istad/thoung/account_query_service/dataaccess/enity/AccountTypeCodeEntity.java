package co.istad.thoung.account_query_service.dataaccess.enity;

import co.istad.thoung.common.domain.valueobject.AccountTypeId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts_type")
public class AccountTypeCodeEntity {

    @Id
    UUID accountTypeCodeId;

    AccountTypeId accountTypeId;

}
