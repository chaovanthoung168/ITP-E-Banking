package co.istad.thoung.account_service.application.repository;

import co.istad.thoung.account_service.data.entity.AccountTypeCodeEntity;
import co.istad.thoung.account_service.domain.valueobject.AccountTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountTypeCodeRepository extends JpaRepository<AccountTypeCodeEntity, UUID> {

    Optional<AccountTypeCodeEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode);

}
