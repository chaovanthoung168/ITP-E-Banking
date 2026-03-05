package co.istad.thoung.customer_service.rest;

import co.istad.thoung.customer_service.application.CustomerQueryService;
import co.istad.thoung.customer_service.application.dto.query.CustomerResponse;
import co.istad.thoung.customer_service.application.dto.query.PagedCustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryController {

    private final CustomerQueryService customerQueryService;

    @GetMapping
    public PagedCustomerResponse getAllCustomer(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize
    ) {
        return customerQueryService.getAllCustomers(pageNumber, pageSize);
    }

    @GetMapping("/{uuid}/history")
    List<?> getCustomerHistory(@PathVariable UUID uuid){
        return customerQueryService.getCustomerHistory(uuid);
    }
}
