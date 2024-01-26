# LSP 리스코프 치환 원칙

- "상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로 동작해야 한다."
  - 특정 메소드가 상위 타입을 파라미터로 사용한다고 할 때, 그 타입의 하위 타입을 인자로 사용했을 때도 문제 없이 정상적으로 동작해야한다. 
- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙이 필요하다. 
- 단순히 컴파일에 성공하는 것을 넘어서는 이야기

## 리스코프 치환 원칙이 지켜지지 않았을 때의 문제

### 직사각형-정사각형 문제

```java
public class Rectangle {
	
    private int width;
    private int height;

    public void setWidth(final int width) {
        this.width = width;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
```

사각형을 상속하는 정사각형 클래스를 다음과 같이 정의할 수 있다.

```java
public class Square extends Rectangle {

    @Override
    public void setWidth(final int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(final int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}
```