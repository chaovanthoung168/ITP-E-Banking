package co.istad.thoung.common.domain.valueobject;


import co.istad.thoung.common.domain.exception.DomainException;

import java.math.BigDecimal;

public record Money(
        BigDecimal amount,
        Currency currency
) {

    public Money add(Money otherAmount) {
        checkSameCurrency(otherAmount.currency);
        return new Money(this.amount.add(otherAmount.amount), this.currency);
    }

    public Money subtract(Money otherAmount) {
        checkSameCurrency(otherAmount.currency);
        return new Money(this.amount.subtract(otherAmount.amount), this.currency);
    }

    public boolean isLessThen(Money otherAmount) {
        checkSameCurrency(otherAmount.currency);
        return this.amount.compareTo(otherAmount.amount) < 0;
    }

    public boolean isLessThenOrEqual(Money otherAmount) {
        checkSameCurrency(otherAmount.currency);
        return this.amount.compareTo(otherAmount.amount) <= 0;
    }

    public boolean isGreaterThenOrEqual(Money otherAmount) {
        checkSameCurrency(otherAmount.currency);
        return this.amount.compareTo(otherAmount.amount) >= 0;
    }

    public boolean isGreaterThen(Money otherAmount) {
        checkSameCurrency(otherAmount.currency);
        return this.amount.compareTo(otherAmount.amount) > 0;
    }

    public void checkSameCurrency(Currency otherCurrency) {
        if (this.currency != otherCurrency) {
            throw new DomainException("Currency do not match");
        }
    }

}
