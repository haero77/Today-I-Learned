> 이 장의 내용
> - null 참조의 문제점과 null을 멀리해야하는 이유
> - null 대신 Optional: null로부터 안전한 도메인 모델 재구현하기
> - Optional 활용: null 확인 코드 제거하기
> - Optional에 저장된 값을 확인하는 방법
> - 값이 없을 수도 있는 사황을 고려하는 프로그래밍

<!-- TOC -->
* [11.1 값이 없는 상황을 어떻게 처리할까?](#111-값이-없는-상황을-어떻게-처리할까)
  * [null 참조 반환의 문제점](#null-참조-반환의-문제점)
  * [11.1.1 보수적인 자세로 NullPointerException 줄이기](#1111-보수적인-자세로-nullpointerexception-줄이기)
    * [null 안전 시도 1: 깊은 의심](#null-안전-시도-1-깊은-의심)
    * [null 안전 시도 2: 너무 많은 출구](#null-안전-시도-2-너무-많은-출구)
    * [_null로 값이 없다는 사실을 표현하는 것은 좋은 방법이 아냐._](#_null로-값이-없다는-사실을-표현하는-것은-좋은-방법이-아냐_)
* [11.2 Optional 클래스 소개](#112-optional-클래스-소개)
* [11.3 Optional 적용 패턴](#113-optional-적용-패턴)
* [11.4 Optional을 사용한 실용 예제](#114-optional을-사용한-실용-예제)
* [11.5 마치며](#115-마치며)
<!-- TOC -->

# 11.1 값이 없는 상황을 어떻게 처리할까?

```java
public class Person {
    private Car car;
    public Car getCar() { return car; }
}
public class Car {
    private Insurance insurance;
    public Insurance getInsurance() { return insurance; }
}
public class Insurance {
    private String name;
    public String getName() { return name; }
}
```

## null 참조 반환의 문제점

```java
public String getCarInsuranceName(Person person) {
    return person.getCar().getInsurance().getName();
}
```

- getCar() 메서드가 null을 반환한다면, null.getInsurance()에서 NPE 발생

<br>

## 11.1.1 보수적인 자세로 NullPointerException 줄이기

### null 안전 시도 1: 깊은 의심

```java
public String getCarInsuranceName(Person person) {
    if (person != null) {
        Car car = person.getCar();
        if (car != null) {
            Insurance insurance = car.getInsurance();
            if (insurance != null) {
                return insurance.getName();
            }
        }
    }
    return "Unknown";
}
```

- 상식적으로 모든 회사에는 이름이 있으므로 보험회사의 이름에 대한 null 체크는 X.
- 모든 변수가 null인지 의심하므로, 변수 접근 마다 if 중첩이 늘어나면서 뎁스 증가.
  - 뎁스 증가되면 코드 구조가 엉망이 되고 가독성이 나빠짐

<br>

### null 안전 시도 2: 너무 많은 출구

```java
public String getCarInsuranceName(Person person) {
    if (person == null) {
        return "Unknown";
    }
	
    Car car = person.getCar();
    if (car == null) {
        return "Unknown";
    }
	
    Insurance insurance = car.getInsurance();
    if (insurance == null) {
        return "Unknown";
    }
	
    return insurance.getName();
}
```

- _메서드에 4개의 출구가 생겼기 때문에, 그렇게 좋은 코드는 아님._
- **출구 때문에 유지보수가 어려워진다.**

<br>

### _null로 값이 없다는 사실을 표현하는 것은 좋은 방법이 아냐._

- 만약 누군가가 null일 수 있다는 사실을 깜빡하면 👉 NPE 맛 좀 보자.

---


# 11.2 Optional 클래스 소개

# 11.3 Optional 적용 패턴

# 11.4 Optional을 사용한 실용 예제

# 11.5 마치며

