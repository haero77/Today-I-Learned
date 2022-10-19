package sample_code.java_for_framework.java_interface.functions_of_interface;

public class Main {
    public static void main(String[] args) {
        // 설정파일, config를 통해 LoginType을 정할 수 있다.
        // 호스트 코드(최종으로 실행하는 코드)만 수정하면 된다. -> 구현 코드에는 수정이 필요없게 된다.
        new Main().run(LoginType.NAVER); // 호스트 코드
    }

    private void run(LoginType type) {
        Login user = getLogin(type);
        user.login();
    }

    // 다형성 적용
    // `new`를 통해 객체를 생성하는 기능을 위임 -> `factory 패턴`
    private Login getLogin(LoginType type) {
        if (type.equals(LoginType.KAKAO)) {
            return new KakaoLogin();
        }
        return new NaverLogin();
    }
}
