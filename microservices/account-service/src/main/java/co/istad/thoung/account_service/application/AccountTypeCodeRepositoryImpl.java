package co.istad.thoung.account_service.application;

import co.istad.thoung.account_service.application.ports.output.repostitory.AccountTypeCodeRepository;
import co.istad.thoung.account_service.dataaccess.entity.AccountTypeCodeEntity;
import co.istad.thoung.account_service.dataaccess.mapper.AccountTypeCodeJpaRepository;
import co.istad.thoung.common.domain.valueobject.AccountTypeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountTypeCodeRepositoryImpl implements AccountTypeCodeRepository {

    private final AccountTypeCodeJpaRepository jpaRepository;

    @Override
    public Optional<AccountTypeCodeEntity> findByCode(AccountTypeCode code) {
        return jpaRepository.findByAccountTypeCode(code);
    }
}
