부제: 테스트 코드의 `@Transactional` 사용할 때의 부작용과 `deleteAllInBatch`의 동작원리에 대해

> 🎯 GOAL
> - 테스트 코드에서 `@Transactional` 사용할 때 생길 수 있는 부작용을 이해한다.
> - `deleteAll()`과 `deleteAll()`의 차이점을 이해하고, 선택 기준을 정할 수 있다.

# 들어가며

최근 [테스트 코드 강의](https://www.inflearn.com/course/practical-testing-%EC%8B%A4%EC%9A%A9%EC%A0%81%EC%9D%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C)를 들으며 공부를 하던 중, 테스트 케이스 종료 후 DB를 초기화하기 위해 `tearDown` 메서드에서 `deleteAll()` 보다는 `deleteAllInBatch()`가 좋다는 내용을 들었다. 본 포스팅에서는 그 이유를 유추해보고, 더불어 테스트 클래스에서 `tearDown` 메서드 대신 `@Transactional`을 사용할 때의 위험성에 대해서도 다뤄본다. 

<br>

# 그냥 `@Transactional`을 사용하면 되지 않을까?

`tearDown` 메서드에서 `deleteAll()`와 `deleteAllInBatch()`의 선택을 고민하기 전에, 이런 생각이 들 수 있다. 

**_그냥 테스트 코드에 `@Transactional`을 사용하면 되는거 아닌가?_**

맞다. 사용해도 된다. 프로덕션 코드에 `@Transactional`을 사용하는 것과는 달리, 테스트 코드에서 `@Transactional`을 사용하면 TC가 끝나고 자동으로 데이터가 롤백되니 귀찮게 `tearDown` 메서드를 작성할 필요도 없다. 그런데, 그 부작용을 인지하지 않은 상태에서 그냥 사용하게 되면 실행 중인 앱이 죽을 수도 있는 심각한 문제를 야기한다. 

간단한 예제를 통해, **테스트 코드의 `@Transactional` 사용의 부작용**이 대체 무엇인지 알아보자


## @Transactional이 가져오는 착각: 문제 없는 프로덕션 코드 

회원 도메인에서, 예제를 가져왔다. 

```java
// 도메인

```
```java
// 비즈니스 로직

```
```java
// 테스트 코드

```

<br>

### `@Transactional`을 사용한 경우 

<br>

### `tearDown` 메서드를 사용한 경우

<br>

## 위험성을 인지하고 사용하자 

<br>

# deleteAll() Vs. deleteAllInBatch()

<br>

## deleteAll() 동작 원리

<br>

## deleteAllInBatch() 동작 원리

<br>

## deleteAllInBatch()를 사용하자

<br>

# 마치며 

### 더 공부해볼 것

### Reference

- [Practical Testing: 실용적인 테스트 가이드 - 인프런](https://www.inflearn.com/course/practical-testing-%EC%8B%A4%EC%9A%A9%EC%A0%81%EC%9D%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C)