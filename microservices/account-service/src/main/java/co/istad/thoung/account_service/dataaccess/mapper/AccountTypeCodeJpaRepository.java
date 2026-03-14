package co.istad.thoung.account_service.dataaccess.mapper;

import co.istad.thoung.account_service.dataaccess.entity.AccountTypeCodeEntity;
import co.istad.thoung.common.domain.valueobject.AccountTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

// infrastructure/repository
public interface AccountTypeCodeJpaRepository extends JpaRepository<AccountTypeCodeEntity, UUID> {
    Optional<AccountTypeCodeEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode);
}
