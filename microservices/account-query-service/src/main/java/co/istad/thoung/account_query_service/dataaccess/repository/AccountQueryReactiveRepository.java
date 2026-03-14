package co.istad.thoung.account_query_service.dataaccess.repository;

import co.istad.thoung.account_query_service.dataaccess.entity.AccountEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface AccountQueryReactiveRepository extends R2dbcRepository<AccountEntity, UUID> {

}
