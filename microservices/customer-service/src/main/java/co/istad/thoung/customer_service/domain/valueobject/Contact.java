package co.istad.thoung.customer_service.domain.valueobject;

import java.util.UUID;

public record Contact(
    UUID contactId,
    String type,
    String number
) {
}
