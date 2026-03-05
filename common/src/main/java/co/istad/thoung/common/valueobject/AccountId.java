package co.istad.thoung.common.valueobject;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AccountId{
    private final UUID value;
    public AccountId(UUID value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
