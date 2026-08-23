package io.refactoring.buckpal.common.validation;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

public class Validation {

  // Validator는 자신을 만든 ValidatorFactory가 닫히기 전까지만 사용할 수 있다.
  // 여기서 팩토리를 바로 닫으면 이후 validate()에서 이 Validator를 호출할 수 없으므로 닫지 않는다.
  private final static Validator validator =
    buildDefaultValidatorFactory().getValidator();

  /**
   * 검사할 객체에 선언된 모든 Bean Validation 애너테이션을 확인한다.
   * 검증에 실패한 항목이 하나라도 있으면 ConstraintViolationException을 발생시킨다.
   */
  public static <T> void validate(T subject) {
    Set<ConstraintViolation<T>> violations = validator.validate(subject);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
