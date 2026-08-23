package io.refactoring.buckpal.application.domain.model;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * 실제 계좌의 현재 스냅샷을 제공.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

  // 계좌를 구분하는 고유 ID
  private final AccountId id;

  /**
   * 거래 내역을 반영하기 전의 계좌 잔액이다.
   * activityWindow에 들어 있는 첫 번째 거래가 발생하기 직전의 잔액을 의미한다.
   */
  @Getter
  private final Money baselineBalance;

  /**
   * 이 계좌에서 최근에 발생한 거래 내역을 모아 둔 객체다.
   */
  @Getter
  private final ActivityWindow activityWindow;

  /**
   * ID가 없는 새 {@link Account}를 만든다.
   * 아직 데이터베이스에 저장되지 않은 새 계좌를 생성할 때 사용한다.
   */
  public static Account withoutId(
    Money baselineBalance,
    ActivityWindow activityWindow
  ) {
    return new Account(null, baselineBalance, activityWindow);
  }

  /**
   * ID가 있는 {@link Account}를 만든다.
   * 데이터베이스에 저장되어 있던 계좌를 다시 객체로 만들 때 사용한다.
   */
  public static Account withId(
    AccountId accountId,
    Money baselineBalance,
    ActivityWindow activityWindow
  ) {
    return new Account(accountId, baselineBalance, activityWindow);
  }

  public Optional<AccountId> getId() {
    return Optional.ofNullable(this.id);
  }

  /**
   * 기준 잔액에 최근 거래 내역을 반영하여 현재 계좌 잔액을 계산한다.
   */
  public Money calculateBalance() {
    return Money.add(
      this.baselineBalance,
      this.activityWindow.calculateBalance(this.id)
    );
  }

  /**
   * 이 계좌에서 주어진 금액의 출금을 시도한다.
   * 잔액이 충분하면 출금 거래 내역을 추가하고, 부족하면 아무것도 변경하지 않는다.
   *
   * @return 출금에 성공하면 true, 잔액이 부족하면 false
   */
  public boolean withdraw(Money money, AccountId targetAccountId) {
    if (!mayWithdraw(money)) {
      return false;
    }

    Activity withdrawal = new Activity(
      this.id,
      this.id,
      targetAccountId,
      LocalDateTime.now(),
      money
    );
    this.activityWindow.addActivity(withdrawal);
    return true;
  }

  /**
   * 현재 잔액에서 출금할 금액을 뺀 뒤에도 잔액이 0 이상인지 확인한다.
   * 즉, 잔액이 부족하지 않아 출금할 수 있는지를 검사한다.
   */
  private boolean mayWithdraw(Money money) {
    return Money.add(
        this.calculateBalance(),
        money.negate()
      )
      .isPositiveOrZero();
  }

  /**
   * 이 계좌에 주어진 금액을 입금하고 새로운 입금 거래 내역을 추가한다.
   *
   * @return 입금에 성공하면 true
   */
  public boolean deposit(Money money, AccountId sourceAccountId) {
    Activity deposit = new Activity(
      this.id,
      sourceAccountId,
      this.id,
      LocalDateTime.now(),
      money
    );
    this.activityWindow.addActivity(deposit);
    return true;
  }

  @Value
  public static class AccountId {
    private Long value;
  }
}
