package co.istad.thoung.account_service.dataaccess.repository;

import co.istad.thoung.account_service.dataaccess.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
}
