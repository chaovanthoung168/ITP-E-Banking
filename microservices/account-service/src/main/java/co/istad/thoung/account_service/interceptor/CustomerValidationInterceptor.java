package co.istad.thoung.account_service.interceptor;

import co.istad.thoung.account_service.client.CustomerServiceClient;
import co.istad.thoung.common.query.ValidateCustomerQuery;
import co.istad.thoung.account_service.domain.command.CreateAccountCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.responsetypes.ResponseTypes;import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.queryhandling.QueryGateway;import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerValidationInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final CustomerServiceClient customerServiceClient;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
            List<? extends CommandMessage<?>> messages) {

        return (index, command) -> {
            if (command.getPayload() instanceof CreateAccountCommand createAccountCommand) {
                log.info("Intercepting CreateAccountCommand for customerId: {}",
                        createAccountCommand.customerId());

                // Validate customer exists
                boolean customerExists = customerServiceClient
                        .existsById(createAccountCommand.customerId().getValue().toString());

                if (!customerExists) {
                    throw new IllegalArgumentException(
                            "Customer not found with id: " + createAccountCommand.customerId().getValue().toString()
                    );
                }

                log.info("Customer validation passed for customerId: {}",
                        createAccountCommand.customerId());
            }

            return command;
        };
    }
}
