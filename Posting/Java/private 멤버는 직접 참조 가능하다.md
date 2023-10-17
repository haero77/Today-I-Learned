# 발단
책 `오브젝트`를 공부하다 이전에 본 적 없는 희한한 코드를 발견했다.
분명 `다른 인스턴스의 private 멤버`임에도 불구하고, 마음껏 그 인스턴스의 private 멤버를 이용하고 있는 것이었다. 분명 private 멤버는 외부에서 접근할 수 없는데, 도대체 어떻게 이게
가능한 것인지 혼란스러웠다. 아래 예시 코드를 보며 상황을 같이 이해해보자.


<br>

# 상황
```java
public class Foo {
    private final int privateValue;

    public Foo(int privateValue) {
        this.privateValue = privateValue;
    }

    public Foo createNewFoo(Foo foo) {
        return new Foo(foo.privateValue); // 인스턴스의 private 멤버를 직접 참조
    }
}
```

위의 createNewFoo() 메서드를 유심히 보자.
다른 인스턴스(정확히는 인스턴스의 주솟값)를 매개변수로 받은 것 까지는 좋다. 문제는 그 다음이다. `foo.privateValue`와 같이 감히 private 멤버에 직접 참조하고 있지 않은가? (private 멤버에 접근하고자 할 때, 대개는 getter 메서드를 통해 private 멤버의 값을 읽어올 수 있었다.) 어떻게 이런 코드가 가능한걸까?

<br>

## 이유

이렇게 'private 멤버에 직접 참조'가 가능한 이유는 바로 접근 제어자 `private` 은 다른 '인스턴스'의 접근을 제한하는 것이 아닌, 다른 '클래스'의 접근을 제한하기 때문이다. (사실 모든 접근 제어자가 이처럼 '인스턴스 레벨'이 아닌 '클래스 레벨'에서 적용된다.)

매개변수로 받은 인스턴스 foo가 다른 인스턴스이기는 하나, 그 역시 '같은' Foo 클래스의 인스턴스 중 하나일 뿐이다. 그리고 같은 클래스 내에서는 private 멤버에 접근 가능하기 때문에, 위와 같이 직접 참조할 수 있는 것이다.

<br>

# 요약

> 💡 접근 제어자는 '인스턴스 레벨'에서 적용되는 것이 아니라,
> '클래스 레벨' 에서 적용된다.

> 💡 `private` 으로 선언된 인스턴스 멤버(변수, 메서드)는 같은 클래스내에서 직접 참조 가능하다.

<br>

# 참고
- [Instances of same class can access private members of each other - HowToDoInJava](https://howtodoinjava.com/java/oops/instances-of-same-class-can-access-private-members-of-each-other/)
- [Why are objects able to access private members of other objects of the same class?](https://www.quora.com/Why-are-objects-able-to-access-private-members-of-other-objects-of-the-same-class)
