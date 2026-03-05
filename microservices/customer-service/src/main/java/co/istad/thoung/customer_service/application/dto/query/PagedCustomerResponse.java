package co.istad.thoung.customer_service.application.dto.query;

import java.util.List;

public record PagedCustomerResponse(
        List<CustomerResponse> content,
        long totalElements,
        int totalPages,
        int pageNumber,
        int pageSize
) {
}
