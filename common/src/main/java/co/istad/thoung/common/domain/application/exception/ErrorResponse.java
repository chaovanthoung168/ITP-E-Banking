package co.istad.thoung.common.domain.application.exception;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record ErrorResponse(
        String status,
        Integer code,
        String message,
        ZonedDateTime timestamp
) {
}
