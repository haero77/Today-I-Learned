package org.example.ch03;

import java.util.Currency;
import java.util.Objects;

public class Money {

    final int amount;
    final Currency currency;

    Money(final int amount, final Currency currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 이상의 값을 지정해주세요.");
        }

        if (Objects.isNull(currency)) {
            throw new IllegalArgumentException("통화 단위를 지정해주세요.");
        }

        this.amount = amount;
        this.currency = currency;
    }

    Money add(final Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("통화 단위가 다릅니다.");
        }

        final int added = this.amount + other.amount;
        return new Money(added, this.currency);
    }

}
