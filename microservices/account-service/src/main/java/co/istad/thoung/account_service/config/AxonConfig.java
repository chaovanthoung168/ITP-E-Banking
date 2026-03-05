package co.istad.thoung.account_service.config;


import co.istad.thoung.account_service.interceptor.CustomerValidationInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    public void registerInterceptors(CommandBus commandBus,
                                     CustomerValidationInterceptor interceptor) {

        // This registers the interceptor globally on the command bus.
        // Your interceptor already has an 'if instanceof CreateAccountCommand'
        // check, so it will only run when it needs to.
        commandBus.registerDispatchInterceptor(interceptor);
    }
}
