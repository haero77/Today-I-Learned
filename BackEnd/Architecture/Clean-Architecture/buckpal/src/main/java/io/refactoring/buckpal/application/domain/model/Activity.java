package io.refactoring.buckpal.application.domain.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 두 {@link Account} 사이에서 발생한 한 건의 송금 내역을 나타낸다.
 */
@Value
@RequiredArgsConstructor
public class Activity {

  @Getter
  private ActivityId id;

  /**
   * 이 거래 내역을 보관하고 있는 계좌의 ID다.
   * 같은 송금도 출금 계좌와 입금 계좌에 각각 기록될 수 있으므로 어느 계좌의 내역인지 구분할 때 사용한다.
   */
  @Getter
  @NonNull
  private final Account.AccountId ownerAccountId;

  /**
   * 돈이 빠져나간 출금 계좌의 ID다.
   */
  @Getter
  @NonNull
  private final Account.AccountId sourceAccountId;

  /**
   * 돈이 들어온 입금 계좌의 ID다.
   */
  @Getter
  @NonNull
  private final Account.AccountId targetAccountId;

  /**
   * 송금이 발생한 날짜와 시간이다.
   */
  @Getter
  @NonNull
  private final LocalDateTime timestamp;

  /**
   * 두 계좌 사이에서 이동한 금액이다.
   */
  @Getter
  @NonNull
  private final Money money;

  public Activity(
    @NonNull Account.AccountId ownerAccountId,
    @NonNull Account.AccountId sourceAccountId,
    @NonNull Account.AccountId targetAccountId,
    @NonNull LocalDateTime timestamp,
    @NonNull Money money
  ) {
    this.id = null;
    this.ownerAccountId = ownerAccountId;
    this.sourceAccountId = sourceAccountId;
    this.targetAccountId = targetAccountId;
    this.timestamp = timestamp;
    this.money = money;
  }

  @Value
  public static class ActivityId {
    private final Long value;
  }
}
