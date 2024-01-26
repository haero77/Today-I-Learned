# 2. 테스트 이름 표시하기

## 테스트 메서드 식별자 컨벤션

- `camelCase`가 아닌 `snake_case` 를 사용한다.

## @DisplayNameGeneration()

![img_1.png](../images/img_1.png)


- 클래스, 메서드 모두 사용가능하다.
- 기본 구현체로 ReplaceUnderscores 제공

## @DisplayName

- 어던 테스트인지 테스트 이름을 보다 쉽게 표현할 수 있는 방법을 제공하는 어노테이션
- `@DisplayNameGeneration` 보다 우선순위가 높다.

> 테스트의 행위를 `@DisplayName` 으로 나타낼 수 있기 때문에, `@DisplayNameGeneration` 보다는 `@DisplayName` 를 사용하는 편이 낫다.
