package co.istad.thoung.common.domain.valueobject;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CustomerId {
    private final    UUID value;

    public CustomerId(UUID value){
        this.value = value;
    }
    @Override
    public String toString(){
        return value.toString();
    }
}

