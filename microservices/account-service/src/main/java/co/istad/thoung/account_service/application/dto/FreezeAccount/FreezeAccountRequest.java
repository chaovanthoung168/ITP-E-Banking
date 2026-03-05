package co.istad.thoung.account_service.application.dto.FreezeAccount;

public record FreezeAccountRequest(
        String reason,
        String requestBy
) {
}
