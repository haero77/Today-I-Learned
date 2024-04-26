package org.example.ch03;

import java.util.Currency;
import java.util.Objects;

public class Money {

    int amount;
    Currency currency;

    public Money(final int amount, final Currency currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 이상의 값을 지정해주세요.");
        }

        if (Objects.isNull(currency)) {
            throw new IllegalArgumentException("통화 단위를 지정해주세요.");
        }

        this.amount = amount;
        this.currency = currency;
    }

}
