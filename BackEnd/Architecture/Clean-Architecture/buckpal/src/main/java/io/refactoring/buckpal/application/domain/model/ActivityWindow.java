package io.refactoring.buckpal.application.domain.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.NonNull;

/**
 * 계좌에서 최근에 발생한 거래 내역을 모아 둔 객체다.
 */
public class ActivityWindow {

  /**
   * 이 객체가 관리하는 거래 내역 목록이다.
   */
  private List<Activity> activities;

  /**
   * 보관 중인 거래 내역 가운데 가장 오래된 거래의 시간을 반환한다.
   */
  public LocalDateTime getStartTimestamp() {
    return activities.stream()
      .min(Comparator.comparing(Activity::getTimestamp))
      .orElseThrow(IllegalStateException::new)
      .getTimestamp();
  }

  /**
   * 보관 중인 거래 내역 가운데 가장 최근 거래의 시간을 반환한다.
   *
   * @return 가장 최근 거래가 발생한 날짜와 시간
   */
  public LocalDateTime getEndTimestamp() {
    return activities.stream()
      .max(Comparator.comparing(Activity::getTimestamp))
      .orElseThrow(IllegalStateException::new)
      .getTimestamp();
  }

  /**
   * 주어진 계좌의 거래 내역을 모두 계산하여 잔액 변화량을 구한다.
   * 해당 계좌로 들어온 금액은 더하고, 해당 계좌에서 나간 금액은 뺀다.
   */
  public Money calculateBalance(Account.AccountId accountId) {
    Money depositBalance = activities.stream()
      .filter(a -> a.getTargetAccountId().equals(accountId))
      .map(Activity::getMoney)
      .reduce(Money.ZERO, Money::add);

    Money withdrawalBalance = activities.stream()
      .filter(a -> a.getSourceAccountId().equals(accountId))
      .map(Activity::getMoney)
      .reduce(Money.ZERO, Money::add);

    return Money.add(depositBalance, withdrawalBalance.negate());
  }

  public ActivityWindow(@NonNull List<Activity> activities) {
    this.activities = activities;
  }

  public ActivityWindow(@NonNull Activity... activities) {
    this.activities = new ArrayList<>(Arrays.asList(activities));
  }

  public List<Activity> getActivities() {
    return Collections.unmodifiableList(this.activities);
  }

  public void addActivity(Activity activity) {
    this.activities.add(activity);
  }
}
