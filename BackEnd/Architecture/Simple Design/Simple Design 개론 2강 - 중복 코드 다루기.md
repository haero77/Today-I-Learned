<!-- TOC -->
* [복습 - 좋은 코드의 기준](#복습---좋은-코드의-기준)
  * [좋은 코드를 만드는법](#좋은-코드를-만드는법)
* [1. 중복 코드는 왜 치명적인가?](#1-중복-코드는-왜-치명적인가)
  * [중복 코드가 치명적인 이유: 중복된 모든 코드를 수정하는 비용과, 버그 발생이라는 사이드 이펙트 야기](#중복-코드가-치명적인-이유-중복된-모든-코드를-수정하는-비용과-버그-발생이라는-사이드-이펙트-야기)
    * [하나의 동작을 수정하기 위해 여러 곳을 고쳐야 하면...](#하나의-동작을-수정하기-위해-여러-곳을-고쳐야-하면)
  * [중복 코드는 잘못된 설계로 인한 '증상'이기도 하다.](#중복-코드는-잘못된-설계로-인한-증상이기도-하다)
    * [중복 코드를 만드는 코드 스멜의 예시](#중복-코드를-만드는-코드-스멜의-예시)
  * [그럼 중복 코드 다루기가 더 어려운가? (리팩토링, 코드 스멜 등을 전부 완벽하게 숙지해야하나?)](#그럼-중복-코드-다루기가-더-어려운가-리팩토링-코드-스멜-등을-전부-완벽하게-숙지해야하나)
    * [중복 코드만 신경쓰면 된다.](#중복-코드만-신경쓰면-된다)
* [2. 조건문의 중복](#2-조건문의-중복)
  * [중복 코드를 찾아내고 제거하는 방법](#중복-코드를-찾아내고-제거하는-방법)
  * [중복 코드 중 가장 나쁜 유형: 조건문의 중복](#중복-코드-중-가장-나쁜-유형-조건문의-중복)
    * [사례 1. 쇼핑몰의 등급제](#사례-1-쇼핑몰의-등급제)
    * [사례 1. 해결책 - 다형성](#사례-1-해결책---다형성)
    * [사례 2. 디버그 모드 분기도 확산된다](#사례-2-디버그-모드-분기도-확산된다)
    * [사례 2-1. 환경변수도 다형성이 가능하다 - Logger](#사례-2-1-환경변수도-다형성이-가능하다---logger)
    * [사례 2-2. 환경변수와 클래스 다형성의 조합](#사례-2-2-환경변수와-클래스-다형성의-조합)
  * [확산되기 쉬운 IF 조건의 예](#확산되기-쉬운-if-조건의-예)
  * [다형성: 조건문 중복을 제거하는 방법](#다형성-조건문-중복을-제거하는-방법)
    * [다양한 다형성의 구현 방식](#다양한-다형성의-구현-방식)
  * [다형성만이 조건문 중복을 해결하는 정답인가?](#다형성만이-조건문-중복을-해결하는-정답인가)
    * [IF문을 줄이는 또 다른 방법들](#if문을-줄이는-또-다른-방법들-)
  * [불필요한 조건문 - True/False를 리턴하는 조건문](#불필요한-조건문---truefalse를-리턴하는-조건문)
    * [⭐️ 함수 실행 성공 여부를 리턴하지 말고 예외를 던져라](#-함수-실행-성공-여부를-리턴하지-말고-예외를-던져라-)
* [3. 부울 논리표를 이용한 조건문의 간소화](#3-부울-논리표를-이용한-조건문의-간소화)
* [4. 본질적인 복잡성과 우발적인 복잡성, 그리고 약간 비슷한 중복 코드를 제거하는 법](#4-본질적인-복잡성과-우발적인-복잡성-그리고-약간-비슷한-중복-코드를-제거하는-법)
* [5. 인지하기 어려운 중복코드의 유형, 중복코드 제거를 도와주는 AI 등의 도구들](#5-인지하기-어려운-중복코드의-유형-중복코드-제거를-도와주는-ai-등의-도구들)
<!-- TOC -->

# 복습 - 좋은 코드의 기준

- 좋은 코드의 기준? 
- 가독성(코드는 읽기 쉬워야한다.)이 좋은 코드의 기준인가?
  - 그런데 가독성의 기준이 애매모호함.
    - 대상: 누가 읽기 쉬어야하나?
      - 나? 팀장? CTP? 왕초보? 신규 입사자?
      - 👉 가독성은 사람마다 다른, 주관적인 기준이다.
- 그래서, 가독성은 해로운 기준임
  - 자신이 이해하지 못하는 코드를 나쁜 코드로 취급하는 것에 당위성을 부여하는 기준.

## 좋은 코드를 만드는법

1. 중복 코드를 제거한다.
2. 그러면서 구성요소를 줄일 방법을 찾는다.

(켄트백의 원래 버전)

3. 테스트를 모두 통과시킨다.
4. 의도를 드러낸다.

# 1. 중복 코드는 왜 치명적인가?

## 중복 코드가 치명적인 이유: 중복된 모든 코드를 수정하는 비용과, 버그 발생이라는 사이드 이펙트 야기

- **_중복 코드 중 일부를 수정하려면 중복된 모든 코드를 찾아서 수정해야한다._** (이게 가장 치명적임!!)

### 하나의 동작을 수정하기 위해 여러 곳을 고쳐야 하면...
 
1. 수정하는 비용이 커진다.
2. 수정 비용이 커지면, 수정에 대해 저항이 커진다. (수정이 꺼려진다.)
   1. 점점 코드를 건드리기 어려워진다.
   2. 누가 요청한 기능에 대해서 '저항을 하는 경우'가 생김. (_"그 기능을 개발하려면 개발 기간이 오래 걸린다~")_
      1. 결국 팀의 속도가 느려짐
3. **_(저항을 뚫고 수정하더라도) 수정 중 사람의 실수로 인해 버그가 생길 가능성이 커진다._**
   1. 중복 코드가 완전히 같은 코드는 아니라서, copy & paste 해결되지 않는 경우가 많다.
   2. 수정해야할 곳을 빠뜨리는 것도 버그의 주요 원인이다.

👉 중복 코드는 코드 품질 문제 중에서 버그 발생 가능성을 직접적으로 높이는 문제다.

## 중복 코드는 잘못된 설계로 인한 '증상'이기도 하다.

- 단순 코드 복붙으로 인해 생긴다기보다, 잘못된 설계로 인해 중복 코드가 생길 수 있다.

### 중복 코드를 만드는 코드 스멜의 예시

- Long Method는 대개 코드의 일부분이 다른 Long Method와 겹친다.
  - 함수가 길다는 것은, 그 함수가 하나의 일만 하는 것이 아닌 여러 개의 일을 하고 있을 가능성이 크다.
  - 즉, 그 여러 개의 일 중에 일부가 다른 곳에서도 필요할 가능성이 높다. 그러면 다른 Long Method와 겹치는 부분이 생기는 경우가 많아진다. 
- Primitive Obsession은 여러 개의 필드를 다루는 코드가 여기저기서 나타난다.
  - 여러 개의 기본 타입 필드를 코드에서 그냥 다루게 되면, 이런 부분이 한 군데만 나타나는 것이 아니라 그 필드를 뭉쳐서 다른 함수로 전달하고, 또 그 다른 함수에서 값을 바꾸고 저장하고.. 이런 일들이 발생한다.
  - 이런 일들이 발생 하면서 파라미터도 비슷하게 다 들고 다니고, 그것을 액세스하는 코드도 곳곳에 나타나고.
- Long Parameter List가 있는 경우, 대개 비슷한 파라미터를 가지는 메서드가 여러 개 있다. 
- Switch Statement는 비슷한 조건 검사가 여러 곳에서 반복되는 경우가 많다. 
- Middle Man이 있는 경우, 그것을 액세스하는 코드가 곳곳에 나타난다.

## 그럼 중복 코드 다루기가 더 어려운가? (리팩토링, 코드 스멜 등을 전부 완벽하게 숙지해야하나?)

- 아니, 그렇지 않다. 오히려 그래서 더 설계 문제를 수정하기 쉬워진다.
- 우리는 중복 코드만 신경 쓰면 된다.

### 중복 코드만 신경쓰면 된다.

<img alt="img_2.png" src="images/img_2.png" width="400"/>

- 우리는 중복 코드 한놈만 패면 된다.
- 리팩토링과 코드 스멜을 잘 알고 있으면 좋지만, 몰라도 된다.
- **_중복 코드만 열심히 잡아도 대부분의 설계 문제를 풀 수 있다._**
  - 중복 코드를 제거하는 과정에서, 이 중복 코드를 유발했던 다른 설계 원인들도 같이 고쳐지는 경우가 많다.

# 2. 조건문의 중복

## 중복 코드를 찾아내고 제거하는 방법

1. 조건문의 중복과 다형성
2. 약간 다른 중복 코드 다루기
3. 인지하기 어려운 중복, 그리고 문서화 
4. 내버려둬도 괜찮은 중복
5. 중복 코드 제거를 도와주는 도구들

## 중복 코드 중 가장 나쁜 유형: 조건문의 중복

- 조건문의 중복이 가장 나쁜 이유
  - 중복 코드는 확산된다.
  - 그 중에 조건문은 더 많이 확산되는 경향이 있다.

### 사례 1. 쇼핑몰의 등급제

```java
public class Order {
  // ... 기타 필드들
  
  // 배송비 계산
  public int calculateShippingFee() {
    if (customer.getLevel() == MemberLevel.VIP && orderAmount > 10000) {
      return 0;
    } else if (customer.getLevel() == MemberLevel.GOLD && orderAmount > 20000) {
      return 0;
    } else {
      return DEFAULT_SHIPPING_FEE;
    }
  }

  // 포인트 계산
  public int calculateOrderPoint() {
    if (customer.getLevel() == MemberLevel.VIP) {
      return (int) (orderAmount * 0.05);
    } else if (customer.getLevel() == MemberLevel.GOLD) {
      return (int) (orderAmount * 0.03);
    } else {
      return 0;
    }
  }
}
```

- 회원 등급별로 다른 혜택을 주기 시작하면, 그 혜택의 종류가 점점 늘어나고, 그에 따라 if문도 늘어난다. (MemberLevel을 체크하는 if-else 문이 세 군데의 서로 다른 메서드에 흩어져 있음.)

```java
// class Order
public int getBirthdayCouponAmount(Customer customer) {
    // 앞의 코드에서도 MemberLevel이 VIP냐, GOLD냐 검사하는 부분이 반복된다.
    if (customer.getLevel() == MemberLevel.VIP) { 
        return 10000;
    } else if (customer.getLevel() == MemberLevel.GOLD) {
        return 5000;
    } else {
        return 0;
    }
}
```

### 사례 1. 해결책 - 다형성

- 다형성을 이용한 해결: **"등급별로 달라지는 것들을 각 등급 클래스에 맡긴다"**

```java
// 1. 등급별 행위를 정의하는 인터페이스
public interface MemberLevel {
    int calculateShippingFee(long orderAmount);
    int calculateOrderPoint(long orderAmount);
    int getBirthdayCouponAmount();
}

// 2. VIP 등급의 구체적인 로직 구현
public class VipLevel implements MemberLevel {
    private static final double POINT_RATE = 0.05;

    @Override
    public int calculateShippingFee(long orderAmount) {
        return orderAmount > 10000 ? 0 : 3000;
    }

    @Override
    public int calculateOrderPoint(long orderAmount) {
        return (int) (orderAmount * POINT_RATE);
    }

    @Override
    public int getBirthdayCouponAmount() {
        return 10000;
    }
}

// 3. GOLD 등급의 구체적인 로직 구현
public class GoldLevel implements MemberLevel {
    private static final double POINT_RATE = 0.03;

    @Override
    public int calculateShippingFee(long orderAmount) {
        return orderAmount > 20000 ? 0 : 3000;
    }

    @Override
    public int calculateOrderPoint(long orderAmount) {
        return (int) (orderAmount * POINT_RATE);
    }

    @Override
    public int getBirthdayCouponAmount() {
        return 5000;
    }
}
```

- 이제 Order 클래스는 등급이 무엇인지 몰라도 되며, 단지 각 등급 객체에 메시지를 전달하기만 하면 된다.

```java
public class Order {
    private Customer customer;
    private long orderAmount;

    // 등급 객체에 로직을 완전히 위임하여 중복 조건문을 제거함
    public int calculateShippingFee() {
        return customer.getLevel().calculateShippingFee(orderAmount);
    }

    public int calculateOrderPoint() {
        // 언어마다 클래스 함수 말고도 변수에 대해서도 다형성 구현 가능 -> 더 간결해짐
        // return customer.getLevel().ORDER_POINT_RATE * orderAmount;
        // return customer.getLevel().getOrderPointRate() * orderAmount;
        return customer.getLevel().calculateOrderPoint(orderAmount);
    }

    public int giveBirthdayCoupon() {
        return customer.getLevel().getBirthdayCouponAmount();
    }
}
```

### 사례 2. 디버그 모드 분기도 확산된다

- 비즈니스 사이드 문제 뿐만 아니라, 기술적인 if문도 많이 생긴다.

```js
if (process.env.NODE_ENV === "development") {
  console.log("Debug information:", value);
}

if (process.env.NODE_ENV === "production") {
  trackUserEvent('user_logged_in')
}

if (process.env.NODE_ENV === "production") {
  trackUserEvent('purchase_completed')
}
```

- 예를 들어, 유저가 가입했을 때, 행동 추적은 하고 싶지만, 환영 메일은 안 보내고 싶을 수 있음
  - 테스트용 메일인데 잘못해서 실제 유저에게 메일이 가버리거나할 수 있기 때문.
  - 실제 실물 카드에서 결제되는 등 사고 발생 가능.

### 사례 2-1. 환경변수도 다형성이 가능하다 - Logger

- 이런 문제는 환경 변수의 다형성을 활용하여 해결

```js
// app.js
logger.trace("Debug information.", { value });

// Logger.js
await configure({
  sinks: { console: getConsoleSink() },
  loggers: [{
    category: "my-app",
    lowestLevel: process.env.LOG_LEVEL, // 환경변수의 다형성 활용
    sinks: ["console"]
  }]
});

// .env.development
LOG_LEVEL=debug

// .env.production
LOG_LEVEL=info
```

- NODE_ENV를 직접 참조하는 것은 금물

```js
// Don't do this
lowestLevel: process.env.NODE_ENV === "production" ? "info" : "debug"
```

- 코드에 NODE_ENV === "production" ? "info" : "debug"라고 박아버리면, 로그 레벨만 따로 바꿀 방법이 없음. 
  - 로그 레벨을 info로 바꾸려면 시스템 전체의 NODE_ENV를 production으로 바꿔야만 하고, 그러면 의도치 않게 다른 모든 기능(결제, DB 연결 등)까지 운영 모드로 돌아가 버린다. (여기서는 로그레벨만 제어하고 싶은 것이 포인트)
- 위 같은 코드는 `if (NODE_ENV === "production")`랑 같은 코드임.

### 사례 2-2. 환경변수와 클래스 다형성의 조합

```js
class ProductionTracker {
  trackUserEvent(event) {
    gtag('event', event); // Google Analytics 이벤트 날리고,
    amplitude.track(event); // Amplitude 이벤트도 날리고...
  }
}

class DevelopmentTracker {
  trackUserEvent(event) {
    console.log("Tracking user event:", event); // 개발모드에서는 데이터가 오염되면 안되므로 로그만 남김
  }
}

// .env.production에는 TRACKER_CLASS=ProductionTracker 로 정의되어 있다.
// 어떤 클래스를 쓸지 환경변수에 정의
const tracker = new globalThis[process.env.TRACKER_CLASS]()
```

## 확산되기 쉬운 IF 조건의 예

- 신규방문자와 재방문자를 구분하는 동작 
- 어드민 계정, 또는 사용자의 권한에 따라 다른 기능 제공 
- API 버전에 따른 분기 
- 플랫폼에 따른 분기 (isMobile, isChrome, isMac, ...)
- 데이터의 상태에 따른 분기 (주문 완료, 배송 완료 등에 따라 달라지는 동작)
- 특정 고객, 또는 파트너를 위한 처리 (상품 진열할 때 A파트너의 상품만 따로 주목도 높은 곳에 진열)
- 테스트 서버 종류별 동작 
- 이벤트 기간에 따라 달라지는 할인 혜택

## 다형성: 조건문 중복을 제거하는 방법

### 다양한 다형성의 구현 방식

- 공통 인터페이스를 상속하는 클래스 다형성
- 환경변수 또는 설정값을 빌드/deploy 시점에 다르게 생성하고 코드에서는 분기하지 않고 사용하기
- 파이썬, 자바스크립트 등의 동적 언어는 다형성을 클래스의 상속 외에도 패키지, 모듈, 함수 등 다양한 단위로 구현할 수 있다.

## 다형성만이 조건문 중복을 해결하는 정답인가?

- 아니다. 다형성은 조건문 중복을 줄이는 고급 기법.
- 다형성외에도 조건문 중복을 줄이는 방법이 있다.
  - 다형성은 다른 문제들이 다 정리됐을 때, 그러고도 남는 if문을 해결하기 위한 좀 고급 기법에 해당.

### IF문을 줄이는 또 다른 방법들 

조건문은 중복되지 않더라도 코드 복잡성을 높이므로, 가능하면 줄이는 것이 좋다. 다형성 외에도 다음과 같은 방법으로 조건문을 줄일 수 있다.

- 논리적으로 조건 자체가 필요한지 따져본다.
  - 완전히 빼도 동작에 아무 차이 없는 경우 있음.
  - 단순 방어 코드: 문제가 일어날지, 아닐지 모르는데, 혹시 모르니까 집어넣은 방어 코드들이 조건문으로 많이 들어가있다.
- Guard Clause(가드 절)를 이용해서 else를 줄인다.
  - 미리 조건 검사를 해서 return이나 예외 throw(early-return)
- 부울 대수(Boolean Algebra)를 이용해서 조건의 관계를 정리해본다.
  - 부울 논리표를 이용해서 조건문을 간소화할 수 있다.
- 중첩 if를 펼쳐서(flatten) 중복 조건이 더 선명하게 드러나게 한 후 중복을 제거한다.
- Reflection을 적절히 이용한다.
- if, else-if, else의 순서를 적절히 조절한다.
- 예외를 던진다.
  - exception의 가치는 try-catch가 아니라 throw하는 것에 있다.
  - 예외를 던짐으로써 flow를 간단히 만들고, 성공하는 케이스에만 집중할 수 있게 만든다.
- try-catch 도 조건문이다!

## 불필요한 조건문 - True/False를 리턴하는 조건문

```python
# 스크래핑하는 코드
def input_password(self, cert: JointCertificate):
    ok_button = self.find_ok_button()
    if ok_button:
        self.driver.execute_script("arguments[0].click();", ok_button)
        self.handle_alert()
        return True
    
    return False
```
- **우리가 Boolean 값을 리턴하는 조건문을 만나면, 뭔가 더 단순하게 만들 방법이 있을 거라고 가정해도 괜찮다.**
  - 항상 그런건 아니지만 많은 경우에 해당.
- 위 코드에서 true/false 리턴해서 이 값을 어디에 쓰는 걸까?

```python
# 인증 팝업 핸들링
def handle_cert_popup(self, cert: JointCertificate):
    with extract_files(cert.file) as file_paths:
        if not self.input_password(cert):
            return False
            
    return True
```

- 그걸 가지고 또 조건문을 만들고 있다.
- 이 패턴이 반복되는게 심상치 않음!!!

```python
def login_with_cert(self, cert: JointCertificate, business_number: str):
    if not self.handle_cert_popup(cert):
        return False
```

- 같은 패턴이 반복되고 있다.
- `login_with_cert`는 또 어디에서 쓸까?

```python
def login(self, account: AccountInfo, **kwargs):
    if not self.login_with_cert(joint_certificate, business_number):
        raise ScrapingAuthenticationFailed('NHIS 공동인증서 로그인에 실패했습니다.')
```

- 드디어 어디에서 쓰는 건지 나온다.
- 최종적으로 예외를 던지는 것에 활용하고 있는데, 이걸 여기까지 올라와서 던질 필요가 있었을까?
- 이 코드를 쭉 따라가다 보면 ok 버튼이 없을 때 이 예외가 나는 로직임. 그냥 ok 버튼이 없으면 바로 예외를 던지면 되지 않았을까?

### ⭐️ 함수 실행 성공 여부를 리턴하지 말고 예외를 던져라 

```python
def input_password(self, cert: JointCertificate):
    if not ok_button: # 일종의 Guard Clause
        raise ScrapingAuthenticationFailed('NHIS 공동인증서 로그인에 실패했습니다.')
    
    # 앞에서 예외를 던짐으로써 flow를 간단히 만들고, 성공하는 케이스에만 집중할 수 있게 만든다. 
    self.driver.execute_script("arguments[0].click();", ok_button)
    self.handle_alert()

# 예외 위의 함수들은 모든게 정상 동작한다고 가정하고 짜도된다. (상위 함수는 하위 함수의 실패를 일일이 확인하지 않아도된다.)
def handle_cert_popup(self, cert: JointCertificate):
    with extract_files(cert.file) as file_paths:
        self.input_password(cert)
        
def login_with_cert(self, cert: JointCertificate, business_number: str):
    self.handle_cert_popup(cert)
    
def login(self, account: AccountInfo, **kwargs):
    self.login_with_cert(joint_certificate, business_number)
```

- 하위 함수에서 예외를 던졌기 때문에, 상위 함수는 하위 함수의 실패를 일일이 확인하지 않아도된다. (하위 함수가 실패했는지 일일이 확인하는 감시자 역할에서 벗어났다.)
- 중간 함수들이 실패를 전파(return False)하는 책임이 사라졌기 때문에, 오직 성공하는 케이스에 대해서만 작성하면 된다. (어차피 예외가 전파되기 때문)
  - **_이것이 exception의 진정한 가치이다!_**
- 예외를 던지는 시점이 더 빨라졌기 때문에, 예외를 추적하기가 더 쉽다! 
  - 이 코드가 나은 점이 또 있음.
  - 예외 트레이스를 보면 바로 `input_password` 함수에서 예외가 던져졌다는 것을 알 수 있기 때문!
    - 앞의 코드는 `login` 함수에서 예외가 던져진 것처럼 보이기 때문에, 디버깅할 때 `login` 함수부터 따라가야 했음.

# 3. 부울 논리표를 이용한 조건문의 간소화

# 4. 본질적인 복잡성과 우발적인 복잡성, 그리고 약간 비슷한 중복 코드를 제거하는 법

# 5. 인지하기 어려운 중복코드의 유형, 중복코드 제거를 도와주는 AI 등의 도구들