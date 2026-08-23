package io.refactoring.buckpal.application.port.in;

import static io.refactoring.buckpal.common.validation.Validation.validate;

import io.refactoring.buckpal.application.domain.model.Account.AccountId;
import io.refactoring.buckpal.application.domain.model.Money;
import jakarta.validation.constraints.NotNull;

public record SendMoneyCommand(
  @NotNull
  AccountId sourceAccountId,
  @NotNull
  AccountId targetAccountId,
  @NotNull
  @PositiveMoney
  Money money
) {
  public SendMoneyCommand(
    AccountId sourceAccountId,
    AccountId targetAccountId,
    Money money
  ) {
    this.sourceAccountId = sourceAccountId;
    this.targetAccountId = targetAccountId;
    this.money = money;
    validate(this);
  }
}
