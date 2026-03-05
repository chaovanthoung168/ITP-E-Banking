package co.istad.thoung.account_service.application.repository;

import co.istad.thoung.account_service.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
}
