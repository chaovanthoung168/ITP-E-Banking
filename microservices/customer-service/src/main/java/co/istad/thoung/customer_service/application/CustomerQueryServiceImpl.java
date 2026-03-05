package co.istad.thoung.customer_service.application;

import co.istad.thoung.customer_service.application.dto.query.CustomerResponse;
import co.istad.thoung.customer_service.application.dto.query.PagedCustomerResponse;
import co.istad.thoung.customer_service.application.projection.GetCustomerQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    @Override
    public PagedCustomerResponse getAllCustomers(int pageNumber, int pageSize) {
        GetCustomerQuery getCustomerQuery = new GetCustomerQuery();
        getCustomerQuery.setPageNumber(pageNumber);
        getCustomerQuery.setPageSize(pageSize);

        return queryGateway
                .query(getCustomerQuery, ResponseTypes.instanceOf(PagedCustomerResponse.class))
                .join();
    }

    @Override
    public List<?> getCustomerHistory(UUID customerId) {

        return eventStore.readEvents(customerId.toString())
                .asStream()
                .map(Message::getPayload)
                .toList();
    }

}
