package co.istad.thoung.account_query_service.rest;

import co.istad.thoung.account_query_service.applicationservice.dto.AccountQueryResponse;
import co.istad.thoung.account_query_service.applicationservice.ports.input.service.AccountQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    @GetMapping("/{accountId}")
    Mono<AccountQueryResponse> getByAccountId(@PathVariable UUID accountId){
        return accountQueryService.getByAccountId(accountId);
    }

}
