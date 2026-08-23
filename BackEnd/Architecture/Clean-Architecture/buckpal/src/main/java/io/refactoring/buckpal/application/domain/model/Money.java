package io.refactoring.buckpal.application.domain.model;

import java.math.BigInteger;
import lombok.NonNull;
import lombok.Value;

/**
 * 계좌에서 사용하는 금액을 나타내는 값 객체다.
 * 금액을 직접 변경하지 않고, 연산할 때마다 새로운 {@link Money} 객체를 반환한다.
 */
@Value
public class Money {

  // 금액이 0인 경우 재사용하는 객체
  public static Money ZERO = Money.of(0L);

  @NonNull
  private final BigInteger amount;

  /** long 값을 Money 객체로 만든다. */
  public static Money of(long value) {
    return new Money(BigInteger.valueOf(value));
  }

  /** 두 금액을 더한 결과를 반환한다. */
  public static Money add(Money a, Money b) {
    return new Money(a.amount.add(b.amount));
  }

  /** 금액이 0 이상인지 확인한다. */
  public boolean isPositiveOrZero() {
    return this.amount.compareTo(BigInteger.ZERO) >= 0;
  }

  /** 금액이 0보다 작은지 확인한다. */
  public boolean isNegative() {
    return this.amount.compareTo(BigInteger.ZERO) < 0;
  }

  /** 금액이 0보다 큰지 확인한다. */
  public boolean isPositive() {
    return this.amount.compareTo(BigInteger.ZERO) > 0;
  }

  /** 이 금액이 주어진 금액보다 크거나 같은지 확인한다. */
  public boolean isGreaterThanOrEqualTo(Money money) {
    return this.amount.compareTo(money.amount) >= 0;
  }

  /** 이 금액이 주어진 금액보다 큰지 확인한다. */
  public boolean isGreaterThan(Money money) {
    return this.amount.compareTo(money.amount) >= 1;
  }

  /** 이 금액에서 주어진 금액을 뺀 결과를 반환한다. */
  public Money minus(Money money) {
    return new Money(this.amount.subtract(money.amount));
  }

  /** 이 금액에 주어진 금액을 더한 결과를 반환한다. */
  public Money plus(Money money) {
    return new Money(this.amount.add(money.amount));
  }

  /** 첫 번째 금액에서 두 번째 금액을 뺀 결과를 반환한다. */
  public static Money subtract(Money a, Money b) {
    return new Money(a.amount.subtract(b.amount));
  }

  /** 부호를 반대로 바꾼 금액을 반환한다. 예를 들어 100은 -100이 된다. */
  public Money negate() {
    return new Money(this.amount.negate());
  }
}
