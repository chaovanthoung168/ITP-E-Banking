package co.istad.thoung.customer_service.domain.valueobject;

public record CustomerEmail(
        String primaryEmail,
        String secondaryEmail
) {
}
