package co.istad.thoung.common.query;

import co.istad.thoung.common.domain.valueobject.CustomerId;

public record ValidateCustomerQuery(
        CustomerId customerId
) {
}
