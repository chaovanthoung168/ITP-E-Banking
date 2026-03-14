package co.istad.thoung.account_service.application.ports.output.repostitory;

import co.istad.thoung.account_service.dataaccess.entity.AccountTypeCodeEntity;
import co.istad.thoung.common.domain.valueobject.AccountTypeCode;

import java.util.Optional;

public interface AccountTypeCodeRepository {
    Optional<AccountTypeCodeEntity> findByCode(AccountTypeCode code);
}
