package io.refactoring.buckpal.application.domain.service;

import io.refactoring.buckpal.application.port.in.SendMoneyCommand;
import io.refactoring.buckpal.application.port.in.SendMoneyUseCase;
import io.refactoring.buckpal.application.port.out.LoadAccountPort;
import io.refactoring.buckpal.application.port.out.UpdateAccountStatePort;

public class SendMoneyService implements SendMoneyUseCase {

  private final LoadAccountPort loadAccountPort;
  private final UpdateAccountStatePort updateAccountStatePort;

  @Override
  public boolean sendMoney(SendMoneyCommand command) {
    // TODO: 비즈니스 규칙 검증
    // TODO: 모델 상태 조작
    // TODO: 출력 반환
  }
}
