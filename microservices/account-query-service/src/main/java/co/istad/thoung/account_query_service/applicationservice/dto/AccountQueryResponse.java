package co.istad.thoung.account_query_service.applicationservice.dto;

import java.util.UUID;

public record AccountQueryResponse(
        UUID accountId,
        String accountNO
) {
}
