package co.istad.thoung.customer_service.application.projection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GetCustomerQuery {
    private Integer pageNumber;
    private Integer pageSize;
}
