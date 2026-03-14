package co.istad.thoung.account_query_service.applicationservice.ports.input.service;

import co.istad.thoung.account_query_service.applicationservice.dto.AccountQueryResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

// Input for application service
public interface AccountQueryService {

    Mono<AccountQueryResponse> getByAccountId(UUID accountId);

}
