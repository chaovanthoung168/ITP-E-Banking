package co.istad.thoung.account_query_service.applicationservice;


import co.istad.thoung.account_query_service.applicationservice.dto.AccountQueryResponse;
import co.istad.thoung.account_query_service.applicationservice.mapper.AccountAppDataMapper;
import co.istad.thoung.account_query_service.applicationservice.ports.input.service.AccountQueryService;
import co.istad.thoung.account_query_service.applicationservice.ports.output.repository.AccountQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountQueryRepository accountQueryRepository;
    private final AccountAppDataMapper accountAppDataMapper;

    @Override
    public Mono<AccountQueryResponse> getByAccountId(UUID accountId) {
        return accountQueryRepository.findById(accountId).map(accountAppDataMapper::accountToAccountQueryResponse);
    }

}
