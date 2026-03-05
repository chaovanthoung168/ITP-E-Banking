package co.istad.thoung.account_service.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Value("${customer-service.url}")
    private String customerServiceUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(customerServiceUrl)
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    // Log outgoing requests
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            System.out.printf(">>> Request: %s %s%n", request.method(), request.url());
            return Mono.just(request);
        });
    }

    // Log incoming responses
    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(response -> {
            System.out.printf("<<< Response status: %s%n", response.statusCode());
            return Mono.just(response);
        });
    }
}
