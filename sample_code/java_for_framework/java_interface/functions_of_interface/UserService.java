package sample_code.java_for_framework.java_interface.functions_of_interface;

public class UserService implements Login {
    // Login 과 UserService가 결합
    // Login 인터페이스와 같은 추상체와 결합하면 -> `결합도가 낮아진다.`
    private Login login; // 내부의 정보이므로 캡슐화

    // 의존성을 외부에 맡김으로써, 의존도를 낮출 수 있다.
    // 의존성을 외부로부터 전달 받았다 -> `의존성 주입(Dependency Injection, DI)`
    // Dependency Inversion by DIP(추상체를 통해 의존성 역전) :
    // - 이전까지는 UserService가 KakaoLogin, NaverLogin에 직접 의존했다면,
    //   추상체를 두어 KakaoLogin과 NaverLogin이 Login 에 의존하도록 변경 (의존성 역전)
    public UserService(Login login) { // 다형성
        this.login = login;
    }

    @Override
    public void login() {
        login.login(); // Login 구현체를 통해서 로직을 수행하므로 -> `Login에 의존한다.`
    }
}
