# 생성자를 통해 올바른 상태를 가진 객체를 생성하는 방법

하나의 `영화`에 대해서는 한 개의 `할인 정책`만 설정하고, <br> 
하나의 `할인 정책`에 대해 여러 개의 `할인 조건`을 설정해야하는 경우에는 어떻게 코드를 작성할까?

<br>

### 생성자를 통해 하나의 DiscountPolicy 인스턴스만 받기
```java
public class Movie {
    ... 
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        ...
        this.discountPolicy = discountPolicy;
    }
}

```

<br>

### 생성자를 통해 여러 개의 DiscountCondition 인스턴스 받기
```java
public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions;

    public DiscountPolicy(DiscountCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }
}
```

<br>

> 💡 생성자의 파라미터 목록을 이용해 초기화에 필요한 정보를 전달하도록 강제하면 올바른 상태를
> 가진 객체의 생성을 보장할 수 있다.