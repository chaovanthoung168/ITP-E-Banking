package co.istad.thoung.customer_service.application;

import co.istad.thoung.customer_service.application.dto.query.CustomerResponse;
import co.istad.thoung.customer_service.application.dto.query.PagedCustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerQueryService {

    PagedCustomerResponse getAllCustomers(int pageNumber, int pageSize);
    List<?> getCustomerHistory (UUID customerId);
}
