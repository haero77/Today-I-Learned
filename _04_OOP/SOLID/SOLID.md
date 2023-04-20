# SOLID 

클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리

## SRP(Single Responsibility Principle): 단일 책임 원칙

> _"어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다." - 로버트 C. 마틴_

- 한 클래스는 하나의 책임만 가져야 한다.
  - 여기서 책임은 하나의 기능이라고 보면 된다.
- 하나의 책임이라는 것은 모호하다.
  - 책임은 클 수도 있고, 작을 수도 있다.
  - 책임은 문맥과 상황에 따라 다르다.
- 책임의 기준은 `변경`이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것이다.
- 예) UI 변경, 객체의 생성과 사용을 분리

### 



## OCP(Open Closed Principle): 개방-폐쇄 원칙

## LSP(Liskov Substitution Principle): 리스코프 치환 원칙

## ISP(Interface Segregation Principle): 인터페이스 분리 원칙

## DIP(Dependency Inversion Principle): 의존관계 역전 원칙