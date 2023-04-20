# OCP

"확장에는 열려 있어야 하고, 변경에는 닫혀 있어야 한다."

"기능을 변경하거나 확장할 수 있으면서 그 기능을 사용하는 코드는 수정하지 않는다."

- MemberService 클라이언트가 구현 객체를 직접 선택
  - `MemberRepository m = new MemoryMemberRepository();`
  - `MemberRepository m = new JDBCMemberRepository();`
- **_분명 다형성을 사용했지만, 구현 객체를 변경하려면 클라이언트 코드를 변경해야한다._** 👉 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자를 통해서 해결한다.(스프링 컨테이너가 DI를 통해 해준다.)


![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdqs36A%2FbtqZpfr81Sw%2FGK2ht1F6Ch87rK0FIAmIoK%2Fimg.png)

JDBC 를 사용할 때를 예로 들자. 클라이언트가 데이터베이스를 오라클에서 MySQL로 변경하더라도 Connection을 설정하는 부분 이외에는 수정할 필요가 없다. Connection 부분을 설정 파일로 분리해두면 클라이언트 코드는 단 한 줄도 변경할 필요가 없다. 

## 개방 폐쇄 원칙을 지키는 방법

- `다형성`을 활용한다.
- 인터페이스를 이용하여 `역할`과 `구현`을 분리한다.

## OCP를 지키지 않았을 때의 문제점

추상화와 다형성이 제대로 지켜지지 않은 경우 OCP를 어기게 된다.

### 1. 다운 캐스팅을 한다. 

```java

// Missile, Player, Enemy extends Character

public void drawCharacter(Character character) {
  if(character instanceof Missile) {  // 타입 확인
    Missile missile = (Missile) character; // 타입 다운 캐스팅
    missile.drawSpecific();

  } else {
    character.draw();

  }

}
```

