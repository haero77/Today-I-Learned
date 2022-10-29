![](image/image%20(1).png)

(이미지 출처 : javatutorial.net)

# 들어가며

고사양의 3D게임을 플레이할 떄 게임 맵의 가까운 배경부터 로딩 된다거나, 혹은 수강신청 시 대기 인원이 몇 명 남았는지를 확인하며 F5 를 누를까 말까 고민했던 경험을 하곤 했다.

이러한 상황들에는 `proxy` 개념이 녹아있는데, 소프트웨어 디자인 패턴에도 이러한 개념을 활용하는 패턴이 있다. 오늘의 주인공 `프록시 패턴` 이 바로 그 경우이다. 이 글에서는 먼저 용어 `proxy`의 사전적 정의를 살펴보고, `프록시 패턴`을 왜 사용 하는지, 어떻게 사용하는지 가볍게 훑어본다.

# 프록시?

![](https://velog.velcdn.com/images/balparang/post/1c05f94d-47f7-4b56-b668-25d894fec2fe/image.png)

(출처: 네이버 영어사전)

먼저 용어부터 살펴보자. 사전에 나와있듯이, `proxy`란 어떤 사물, 사람의 대리 역할을 하는 무언가(또는 사람)를 의미한다.

누군가를 만나야하는데, 그 사람의 비서 분을 통해서 먼저 연락을 취해야하는 경우에는 비서 분이 `proxy`가 된다.

소송을 진행 중이라면 변호사 분이 내 법률적 대리인, 즉 `proxy`가 된다. (드라마나 영화에서  ‘내 변호사를 통해서 전달하세요’ 등의 대사를 심심치 않게 찾아볼 수 있다.)

디자인 패턴에서 사용되는 `proxy` 는 사전적 정의의 3번, 즉 ‘측정·계산하려는 다른 것을 대표하도록 이용하는 대용물’에 가깝다고 볼 수 있는데, 다음의 설명과 예제들을 통해 필자가 왜 그렇게 생각하게 되었는지 의견을 나누고자 한다.

# 왜 사용하는가

## 직접 사용하는 것은 부담스럽다.

![](https://velog.velcdn.com/images/balparang/post/aef751e0-0bb1-4d27-a06f-751a6082fff9/image.png)

(이미지 출처: Refactoring.Guru)

여기, 어마어마하게 데이터가 많고, 쿼리 시간 또한 매우 긴 가상의 데이터베이스(객체)를 사용해야한다고 가정하자.  이 DB객체는 요청받은 쿼리를 수행만 하고 다른 작업은 일체하지 않으므로, 우리는 ‘클라이언트가 요청한 작업이 잘 수행되고 있는지’, ‘작업이 완료될 때까지 남은 시간은 얼마인지’는 알 길이 없다. 심지어 작업을 완료하는 것 자체도 많은 시간을 필요로 하니 점차 이 DB객체를 사용하는 것조차 부담스러워진다. 문득 이런 생각이 든다. ‘데이터베이스(객체)를 수정하지 않으면서, 작업의 진행상황 등을 알려주는 객체가 있으면 참 좋을텐데...'라고 말이다. 자, 이제 이를 해결해줄 `Proxy` 가 등장할 차례다.

## 중간에 누가 있었으면…

![](https://velog.velcdn.com/images/balparang/post/8d50407a-d7ed-426e-a2cb-ba21056afda4/image.png)


(이미지 출처: Refactoring.Guru)

방금의 문제(’진행 중인 작업 상황을 알 수 없다.’ 등)를 해결하기 위해 `Proxy` 가 등장한다. 이 친구는 원래의 DB객체를 수정하지 않으면서, 내가 필요하던 기능을 탑재한 그런 이상적인 객체이다. 이 친구는 내가 요청했던 ‘쿼리’ 작업을 DB객체한테 ‘대신’ 요청하고, 중간 중간 작업이 어떻게 진행되는지, 작업이 완료되기까지는 시간이 얼마나 남았는지 등의 정보도 알려준다.  `객체에 대한 수정 없이`, `작업에 대한 요청을 대신 전달`하고, `부가기능` 또한 가능하게 만들어주는 것. 이것이 바로 `Proxy` 객체를 사용하는 이유이다.

# 프록시 패턴


![](https://velog.velcdn.com/images/balparang/post/d6fc536b-ab98-4b0e-bdcc-f927d3a6f313/image.png)




> **특정 객체에 대한 접근을 제어하거나 기능을 추가할 수 있는 패턴**
(출처: 헤드퍼스트 디자인패턴)
>

이번에는 이 `Proxy` 를 활용한 `프록시 패턴` 을 알아보자.

프록시 패턴이란, 클라이언트가 사용하고자 하는 객체(기능)에 직접 접근하는 것이 아닌, Proxy 객체를 통해 접근(사용)하는 패턴을 말한다.

이렇게 함으로써 **객체에 대한 접근을 제어**하거나, 객체를 생성 시 많은 비용이 들어간다면 `지연 초기화` 를 통해 **원하는 시점에 객체를 생성**할 수도 있다. 그 밖에도 실제로 기능을 수행하기 전 **유효성을 검증**한다거나 **로깅을 하는 등**의 추가적인 작업을 원래 사용하고자 했던 객체의 작업 이전/이후 에 할 수 있게된다. 즉, `프록시 패턴` 은 앞서 언급한 `Proxy` 의 장점을 살리기 위한 디자인 패턴이다. 이제, 아래 예제를 보면서 프록시 패턴을 간단히 구현해보자.

<br>

# 프록시 패턴의 구현

## 사전 준비

![](https://velog.velcdn.com/images/balparang/post/d74c27ec-077b-40e6-9166-aad2fe4c143d/image.png)


```java
// Calculator 인터페이스
public interface Calculator {

    int calculate();

}

// Calculator 인터페이스를 상속하는 DefaultCalculator
public class DefaultCalculator implements  Calculator{

    @Override
    public int calculate() {
        return 0; // 이 '0' 하나를 리턴하는데 무려 10초의 시간이 걸린다고 가정하자.
    }
}

// Calculater 객체의 caculate() 기능을 쓰고 싶어하는 Client
public class Client {

    public static void main(String[] args) {
        Calculator calculator = new DefaultCalculator();
        calculator.calculate();
    }
}
```

여기, 계산기 객체의 ‘계산’ 기능을 쓰고 싶어하는 한 Client가 있다. 그런데 계산 기능을 구현해야할 개발자가 게으른 나머지 클라이언트가 원하는 계산과는 관계없이 모든 계산결과를 0으로 반환하는 계산 기능을 만들고 말았다. 이보다 더 최악인 것은 이 계산기는 한 번 계산할 때 무려 10초씩이나 걸린다는 것이다. (정말 어처구니 없는 상황이기는 하다.)

Client는 이제 영문도 모른 채, 계산기능을 사용할 때마다 10초씩이나 견뎌야하는 끔찍한 경험을 할 것이다. 이 Client를 위해서 계산 완료까지 남은 예상 시간을 알려주는 코드를 작성해보자. 물론, 이미 작성한 계산기 코드는 일체 수정하지 않은 상태로 말이다.

<br>

## Proxy 클래스를 만들고, 인터페이스 타입의 레퍼런스를 선언하자

![](https://velog.velcdn.com/images/balparang/post/e79eafdb-47bc-4adf-b78d-24ed66dfcd3a/image.png)



### Proxy 클래스를 만들자.

```java
public class DefaultCalculatorProxy implements Calculator {

    @Override
    public int calculate() {

    }
}
```

첫 단계는 기존의 서비스 객체와 같은 인터페이스를 상속하는 `Proxy` 클래스를 만드는 것이다. 이제 (약간의 작업만 더하면) 이 DefaultCalculatorProxy 객체는 기존의 DefaultCalculator 를 완전히 대체할 수 있다.

<br>

### 인터페이스 타입의 레퍼런스를 선언하고, 원래의 서비스 객체를 참조한다.

```java
public class DefaultCalculatorProxy implements Calculator {

    private Calculator calculator;

    public DefaultCalculatorProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculate() {
        System.out.println("믿을 수 없겠지만, 계산이 완료될 때까지 10초 남았습니다.");

        int calculationResult = calculator.calculate(); // DefaultCalculator에게 계산을 요청한다.

        System.out.println("계산이 완료되었습니다.");
        return calculationResult;
    }
}

// DefaultCalculatorProxy가 DefaultCalculator를 참조할 수 있게 Client 코드를 약간 수정한다. 
public class Client {

    public static void main(String[] args) {
        Calculator calculator = new DefaultCalculatorProxy(new DefaultCalculator());
        calculator.calculate();
    }
}
```

다음 단계로 인터페이스 타입의 레퍼런스를 선언하고, 이 레퍼런스가 원래 서비스 객체인 DefaultCalculator 를 참조할 수 있게 한다. 이어 클래스 내부에 필요한 기능들을 더 추가해줌으로써 Proxy 클래스를 완성한다. 이로써 기존의 계산기 코드는 수정하지 않은 채로 기존의 서비스 객체를 완전히 대체하는 `Proxy` 가 탄생하였다.(이는 객체 지향 설계 5원칙 중 하나인 `OCP`를 자연스럽게 만족한다.)

<br>

### 객체를 생성하는 비용이 크다면

위에서 `Proxy` 객체가 기존의 서비스 객체를 완전히 대체하는 것을 확인할 수 있었다. 그런데 서비스 객체를 생성할 때의 비용이 비싸다면 어떻게 할 것인가? 비용이 비싼 객체를 프로그램 실행 시마다 생성하는 것은 부담스럽다. `지연 초기화` 를 이용하여 이 문제를 해결해보자.

```java
public class DefaultCalculatorProxy implements Calculator {

    private Calculator calculator;

    @Override
    public int calculate() {
        System.out.println("믿을 수 없겠지만, 계산이 완료될 때까지 10초 남았습니다.");

        // 지연 초기화(lazy initialization)
        if (calculator == null) {
            calculator = new DefaultCalculator();
        }
        int calculationResult = calculator.calculate(); // DefaultCalculator에게 계산을 요청한다.

        System.out.println("계산이 완료되었습니다.");
        return calculationResult;
    }
}
```

`Proxy` 객체의 레퍼런스 필드가 가리키는 값이 없을 때 서비스 객체를 생성해줌으로써, 이전 보다는 조금 덜 부담스럽게 계산 기능을 이용할 수 있게 되었다. 또한 더 이상 생성자를 통해 서비스 객체를 주입받지 않아도 되므로, 생성자를 제거하였다.

<bR>

# 장단점

물론 `프록시 패턴` 에도 장점과 단점이 공존한다. 먼저 장점은 다음과 같다.

## 장점

- `SOLID` 원칙 중 `OCP` 와 `SRP` 를 만족한다.
  - 새로운 기능을 추가(확장)하지만 기존 서비스 객체의 변경은 없었다(변경에 대해 닫혀있다). ➡ `OCP`
  - `Proxy` 객체를 따로 둠으로써, 기존 서비스 객체 DefaultCalculator의 책임은 그대로 유지하였다. ➡ `SRP`
- `지연 초기화` 를 이용하거나, 다양한 부가기능(유효성 검증, 로깅 등)을 추가함으로써 유연한 설계가 가능해진다.

<br>

## 단점

- (기존의 클래스가 단순할 수록) 새로운 클래스를 많이 추가해야하므로, 코드의 복잡도가 높아질 수 있다.
  - 서비스의 응답이 느려질 수 있다.

# 마치며

프록시 패턴은 기존 서비스 코드는 변경하지 않은 채로 기능을 추가할 수 있다보니  `보안` , `로깅` , `유효성 검증` 등  다양한 기능을 `Proxy` 객체를 이용하여 구현할 수도 있다.  Java의 `다이나믹 프록시`, `Spring AOP` 에서도 프록시 패턴 을 찾아볼 수 있으며, 이에 대해서는 추후 다른 포스팅에서 심도있게 다뤄보도록 하겠다.

<br>

## 더 공부해야할 것

- `지연 초기화`
- `다이나믹 프록시`
- `스프링 AOP`

<br>

### 참고

> [[Design Pattern] 프록시 패턴(Proxy Pattern)에 대하여](https://coding-factory.tistory.com/711)
> 
> [코딩으로 학습하는 GoF의 디자인 패턴 - 인프런 | 강의](https://www.inflearn.com/course/%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4)
> 
> [Proxy - Refactoring.Guru](https://refactoring.guru/design-patterns/proxy)
> 
> [헤드 퍼스트 디자인 패턴](http://www.yes24.com/Product/Goods/108192370)

