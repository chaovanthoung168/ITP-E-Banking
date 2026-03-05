package co.istad.thoung.account_service.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerServiceClient {

    private final WebClient webClient;

    public boolean existsById(String customerId) {
        log.info("Checking customer exists: {}", customerId);

        String rawResponse = webClient.get()
                .uri("/api/v1/customers/{id}", customerId)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> {
                            log.warn("Customer not found (4xx): {}", customerId);
                            return Mono.empty();
                        }
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(
                                        new RuntimeException("Customer service unavailable: " + body)))
                )
                .bodyToMono(String.class)   // ✅ read raw string — works for any shape
                .defaultIfEmpty("false")
                .doOnSuccess(r -> log.info("Raw response for customer {}: {}", customerId, r))
                .block();

        // ✅ log the actual response so we can see exactly what comes back
        log.info("Customer service raw response: {}", rawResponse);

        // parse plain boolean: "true" / "false"
        if ("true".equalsIgnoreCase(rawResponse)) {
            return true;
        }

        // parse wrapped object: {"exists":true} or {"data":true}
        if (rawResponse != null && rawResponse.contains("true")) {
            return true;
        }

        return false;
    }
}