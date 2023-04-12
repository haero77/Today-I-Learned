package old.sample_code.java_for_framework.java_interface.default_method.def1;

interface MyInterface {
    void method1(); // 구현부가 없는 추상메서드

    // 원래 구현부 있는 메서드를 인터페이스에 선언할 수 없는데, default 키워드를 사용하면 가능하다.
    default void sayHello() {
        System.out.println("Hello World");
    }
}

public class Main implements MyInterface{
    public static void main(String[] args) {
        new Main().sayHello(); // 오버라이드 하지 않은 sayHello 메서드를 호출가능하다. (디폴트 메서드 이기때문)
    }

    @Override
    public void method1() {
        throw new RuntimeException(); // '호출하지 않겠습니다'
    }
}
